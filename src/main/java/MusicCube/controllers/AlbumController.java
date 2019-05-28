package MusicCube.controllers;

import MusicCube.entities.Band;
import MusicCube.entities.Song;
import MusicCube.services.album.AlbumService;
import MusicCube.entities.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /************************ GET ********************************/
    @RequestMapping(value = "/album{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Album> getById(int id) {
        return albumService.getById(id);
    }

    @RequestMapping(value = "/albums",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Album> getAll() {
        return albumService.getAll();
    }

    // --- Get all albums with paging ---
    @RequestMapping(value = "albums/{page}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Album> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> perPage) {
        return albumService.getAllPaging(pageNr,perPage.orElse(10));
    }

    @RequestMapping(value = "/album/bands",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Band> getAlbumBands(int albumId) {
        return albumService.getAlbumBands(albumId);
    }

    @RequestMapping(value = "/albums{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Album> getByAlbumName(String albumName) {
        return albumService.getByAlbumName(albumName);
    }

    @RequestMapping(value = "/album/songs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> getAlbumSongs(int albumId) {
        return albumService.getAlbumSongs(albumId);
    }

    /*************************************************************/


    @RequestMapping(value = "/album",method = RequestMethod.POST)
    public ResponseEntity<Album> create(@RequestBody @Valid @NotNull Album album) {
        albumService.save(album);
        return ResponseEntity.ok().body(album);
    }

    @RequestMapping(value = "/album",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Album album) {
        Optional<Album> album1 = albumService.getById(album.getId());
        if (Objects.nonNull(album1)) {
            albumService.save(album);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/albums",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Album> redirect(Model model) {
        return albumService.getAll();
    }

    @RequestMapping(value = "/album/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        albumService.delete(id);
        return new RedirectView("/api/albums",true);
    }
}
