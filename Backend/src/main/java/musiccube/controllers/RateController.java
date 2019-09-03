package musiccube.controllers;

import musiccube.entities.*;
import musiccube.services.album.AlbumService;
import musiccube.services.artist.ArtistService;
import musiccube.services.band.BandService;
import musiccube.services.rate.RateService;
import musiccube.services.song.SongService;
import musiccube.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BandService bandService;

    @Autowired
    private ArtistService artistService;


    @GetMapping(path = "/rate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> getById(@PathVariable("id") int id) {
        Optional<Rate> rate = rateService.getById(id);
        return rate.isPresent() ?
                ResponseEntity.ok(rate.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Rate> getAll() {
        return rateService.getAll();
    }

    // Creating Rates

    @PostMapping(path = "/rate/song", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> createSongRate(@RequestParam("userName") String userName, @RequestParam("songId") int id, @RequestParam("rate") int rate) {
        User user = userService.getByUserName(userName).orElse(null);
        Song song = songService.getById(id).orElse(null);
        if(user != null && song != null){
            Rate rateSong = new Rate(user, song, rate);
            rateService.save(rateSong);
            return ResponseEntity.ok().body(rateSong);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @PostMapping(path = "/rate/album", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> createAlbumRate(@RequestParam("userName") String userName, @RequestParam("albumId") int id, @RequestParam("rate") int rate) {
        User user = userService.getByUserName(userName).orElse(null);
        Album album = albumService.getById(id).orElse(null);
        if(user != null && album != null){
            Rate rateAlbum = new Rate(user, album, rate);
            rateService.save(rateAlbum);
            return ResponseEntity.ok().body(rateAlbum);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @PostMapping(path = "/rate/band", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> createBandRate(@RequestParam("userName") String userName, @RequestParam("bandId") int id, @RequestParam("rate") int rate) {
        User user = userService.getByUserName(userName).orElse(null);
        Band band = bandService.getById(id).orElse(null);
        if(user != null && band != null){
            Rate rateBand = new Rate(user, band, rate);
            rateService.save(rateBand);
            return ResponseEntity.ok().body(rateBand);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @PostMapping(path = "/rate/artist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> createArtistRate(@RequestParam("userName") String userName, @RequestParam("artistId") int id, @RequestParam("rate") int rate) {
        User user = userService.getByUserName(userName).orElse(null);
        Artist artist = artistService.getById(id).orElse(null);
        if(user != null && artist != null){
            Rate rateArtist = new Rate(user, artist, rate);
            rateService.save(rateArtist);
            return ResponseEntity.ok().body(rateArtist);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    // **************

    @DeleteMapping(path = "/rate/{id}")
    public ResponseEntity<Rate> delete(@PathVariable("id") Integer id) {
        rateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/rate")
    public ResponseEntity<Void> editSongRate(@RequestParam("id") int id, @RequestParam("rate") int rate){

        Rate rateSong = rateService.getById(id).orElse(null);
        if(rateSong != null) {
            rateSong.setRate(rate);
            rateService.save(rateSong);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/rate/getByUserNameAndSongId")
    public ResponseEntity<Rate> getRateByUserNameAndSongId(@RequestParam("userName") String userName, @RequestParam("songId") int id){
        Optional<Rate> rate = rateService.getRateByUserNameAndSongId(userName, id);
        return rate.isPresent() ?
                ResponseEntity.ok(rate.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/rate/getByUserNameAndAlbumId")
    public ResponseEntity<Rate> getRateByUserNameAndAlbumId(@RequestParam("userName") String userName, @RequestParam("albumId") int id){
        Optional<Rate> rate = rateService.getRateByUserNameAndAlbumId(userName, id);
        return rate.isPresent() ?
                ResponseEntity.ok(rate.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/rate/getByUserNameAndBandId")
    public ResponseEntity<Rate> getRateByUserNameAndBandId(@RequestParam("userName") String userName, @RequestParam("bandId") int id){
        Optional<Rate> rate = rateService.getRateByUserNameAndBandId(userName, id);
        return rate.isPresent() ?
                ResponseEntity.ok(rate.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/rate/getByUserNameAndArtistId")
    public ResponseEntity<Rate> getRateByUserNameAndArtistId(@RequestParam("userName") String userName, @RequestParam("artistId") int id){
        Optional<Rate> rate = rateService.getRateByUserNameAndArtistId(userName, id);
        return rate.isPresent() ?
                ResponseEntity.ok(rate.get()) :
                ResponseEntity.notFound().build();
    }

}
