package musiccube.controllers;

import musiccube.entities.*;
import musiccube.services.album.AlbumService;
import musiccube.services.comment.CommentService;
import musiccube.services.rate.RateService;
import musiccube.services.song.SongService;
import musiccube.services.userFavorites.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RateService rateService;

    @Autowired
    private UserFavoritesService userFavoritesService;

    @Autowired
    private SongService songService;

    /************************ GET ********************************/
    @GetMapping(
            path = "/album/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Album> getById(@PathVariable("id") int id) {
        Optional<Album> album = albumService.getById(id);
        return album.isPresent() ?
                ResponseEntity.ok(album.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/album",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Album> getAll() {
        return albumService.getAll();
    }

    // --- Get all albums with paging ---
    @GetMapping(
            path = "album/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Album> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {
        return perPage == null ?
                albumService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                albumService.getAllPaging(pageNr, perPage);
    }

    @GetMapping(
            path = "/album/{id}/bands",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getAlbumBands(@PathVariable("id") int albumId) {
        return albumService.getAlbumBands(albumId);
    }

    @GetMapping(
            path = "/album/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Album> getByAlbumName(@PathVariable("name") String albumName) {
        return albumService.getByAlbumName(albumName);
    }

    @GetMapping(
            path = "/album/{id}/songs",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getAlbumSongs(@PathVariable("id") int albumId) {
        return albumService.getAlbumSongs(albumId);
    }

    /*************************************************************/


    @PostMapping("/admin/album")
    public ResponseEntity<Album> create(@RequestBody @Valid @NotNull Album album) {
        albumService.save(album);
        return ResponseEntity.ok().body(album);
    }

    @PutMapping("/admin/album")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Album album) {
        Optional<Album> album1 = albumService.getById(album.getId());
        if (Objects.nonNull(album1)) {
            albumService.save(album);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/album/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        Album album = albumService.getById(id).orElse(null);
        if(album != null){
            Iterable<Comment> allAlbumComments = commentService.getCommentsByAlbumId(album.getId());
            for (Comment comment : allAlbumComments) {
                commentService.delete(comment.getId());
            }

            Iterable<Rate> allAlbumRates = rateService.getRatesByAlbumId(album.getId());
            for (Rate rate : allAlbumRates) {
                rateService.delete(rate.getId());
            }
            Iterable<UserFavorites> allUserFavoritesLists = userFavoritesService.getAll();
            for (UserFavorites userFavorites : allUserFavoritesLists) {
                Set<Album> userFavoritesAlbums = userFavorites.getFavoriteAlbums();
                if(userFavoritesAlbums.contains(album)) {
                    userFavorites.deleteAlbumFromFavorites(album);
                }
            }

            Iterable<Song> allAlbumSongs = songService.getByAlbumName(album.getAlbumName());
            for (Song song : allAlbumSongs) {
                song.setAlbum(null);
                songService.save(song);
            }


            albumService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
