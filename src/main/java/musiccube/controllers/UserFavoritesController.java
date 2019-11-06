package musiccube.controllers;


import musiccube.entities.*;
import musiccube.services.album.AlbumService;
import musiccube.services.artist.ArtistService;
import musiccube.services.band.BandService;
import musiccube.services.song.SongService;
import musiccube.services.userFavorites.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress")
public class UserFavoritesController {

    @Autowired
    private UserFavoritesService userFavoritesService;

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BandService bandService;

    @Autowired
    private ArtistService artistService;

    @GetMapping(path = "/userFavorites/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserFavorites> getById(@PathVariable("id") int id) {
        Optional<UserFavorites> userFavorites = userFavoritesService.getById(id);
        return userFavorites.isPresent() ?
                ResponseEntity.ok(userFavorites.get()) :
                ResponseEntity.notFound().build();
    }

    // Get Favorite

    @GetMapping(path = "/userFavorites", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserFavorites> getAll() {
        return userFavoritesService.getAll();
    }

    @GetMapping(path = "/userFavorites/song", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Song> getUserFavoriteSongsByUserName(@RequestParam("userName") String userName){
        return userFavoritesService.getUserFavoriteSongsByUserName(userName);
    }

    @GetMapping(path = "/userFavorites/album", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Album> getUserFavoriteAlbumsByUserName(@RequestParam("userName") String userName){
        return userFavoritesService.getUserFavoriteAlbumsByUserName(userName);
    }

    @GetMapping(path = "/userFavorites/band", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Band> getUserFavoriteBandsByUserName(@RequestParam("userName") String userName){
        return userFavoritesService.getUserFavoriteBandsByUserName(userName);
    }

    @GetMapping(path = "/userFavorites/artist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Artist> getUserFavoriteArtistsByUserName(@RequestParam("userName") String userName){
        return userFavoritesService.getUserFavoriteArtistsByUserName(userName);
    }

    @GetMapping(path = "/userFavorites/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<UserFavorites> getByUserName(@RequestParam("userName") String userName){
        return userFavoritesService.getUserFavoriteAllByUserName(userName);
    }

    // Add and delete from list


    @PatchMapping(path = "userFavorites/song/add")
    public ResponseEntity<Void> addSongToFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Song song = songService.getById(id).orElse(null);
        if(song != null && userFavorites != null){
            userFavorites.addSongToFavorites(song);
            userFavoritesService.save(userFavorites);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/song/delete")
    public ResponseEntity<Void> deleteSongFromFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Song song = songService.getById(id).orElse(null);
        if(song != null && userFavorites != null){
            if(userFavorites.deleteSongFromFavorites(song)){
                userFavoritesService.save(userFavorites);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/album/add")
    public ResponseEntity<Void> addAlbumToFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Album album = albumService.getById(id).orElse(null);
        if(album != null && userFavorites != null){
            userFavorites.addAlbumToFavorites(album);
            userFavoritesService.save(userFavorites);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/album/delete")
    public ResponseEntity<Void> deleteAlbumFromFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Album album = albumService.getById(id).orElse(null);
        if(album != null && userFavorites != null){
            if(userFavorites.deleteAlbumFromFavorites(album)){
                userFavoritesService.save(userFavorites);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/band/add")
    public ResponseEntity<Void> addBandToFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Band band = bandService.getById(id).orElse(null);
        if(band != null && userFavorites != null){
            userFavorites.addBandToFavorites(band);
            userFavoritesService.save(userFavorites);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/band/delete")
    public ResponseEntity<Void> deleteBandFromFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Band band = bandService.getById(id).orElse(null);
        if(band != null && userFavorites != null) {
            if(userFavorites.deleteBandFromFavorites(band)){
                userFavoritesService.save(userFavorites);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/artist/add")
    public ResponseEntity<Void> addArtistToFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Artist artist = artistService.getById(id).orElse(null);
        if(artist != null && userFavorites != null){
            userFavorites.addArtistToFavorites(artist);
            userFavoritesService.save(userFavorites);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "userFavorites/artist/delete")
    public ResponseEntity<Void> deleteArtistFromFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = getByUserName(userName).orElse(null);
        Artist artist = artistService.getById(id).orElse(null);
        if(artist != null && userFavorites != null) {
            if(userFavorites.deleteArtistFromFavorites(artist)){
                userFavoritesService.save(userFavorites);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Check if exist in user favorite list

    @GetMapping(path = "/userFavorites/song/exist")
    public boolean existSongInUserFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = userFavoritesService.getUserFavoriteAllByUserName(userName).orElse(null);
        Song song = songService.getById(id).orElse(null);
        if(song != null) {
            return userFavorites.getFavoriteSongs().contains(song);
        }
        else return false;
    }

    @GetMapping(path = "/userFavorites/album/exist")
    public boolean existAlbumInUserFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = userFavoritesService.getUserFavoriteAllByUserName(userName).orElse(null);
        Album album = albumService.getById(id).orElse(null);
        if(album != null) {
            return userFavorites.getFavoriteAlbums().contains(album);
        }
        else return false;
    }

    @GetMapping(path = "/userFavorites/band/exist")
    public boolean existBandInUserFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = userFavoritesService.getUserFavoriteAllByUserName(userName).orElse(null);
        Band band = bandService.getById(id).orElse(null);
        if(band != null) {
            return userFavorites.getFavoriteBands().contains(band);
        }
        else return false;
    }

    @GetMapping(path = "/userFavorites/artist/exist")
    public boolean existArtistInUserFavorites(@RequestParam("id") int id, @RequestParam("userName") String userName){
        UserFavorites userFavorites = userFavoritesService.getUserFavoriteAllByUserName(userName).orElse(null);
        Artist artist = artistService.getById(id).orElse(null);
        if(artist != null) {
            return userFavorites.getFavoriteArtists().contains(artist);
        }
        else return false;
    }

    // Making and deleting whole list (! Use only if you deleting an user !)

    @PostMapping(path = "/userFavorites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserFavorites> create(@RequestBody @Valid @NotNull UserFavorites userFavorites){
        userFavoritesService.save(userFavorites);
        return ResponseEntity.ok().body(userFavorites);
    }

    @DeleteMapping(path = "/userFavorites/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        userFavoritesService.delete(id);
        return new RedirectView("/api/userFavorites", true);
    }

}
