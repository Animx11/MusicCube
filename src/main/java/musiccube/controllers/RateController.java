package musiccube.controllers;

import musiccube.entities.Rate;
import musiccube.entities.Song;
import musiccube.entities.User;
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

    @PostMapping(path = "/rate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rate> create(@RequestParam("userName") String userName, @RequestParam("songId") int id, @RequestParam("rate") int rate) {
        User user = userService.getByUserName(userName).orElse(null);
        Song song = songService.getById(id).orElse(null);
        if(user != null && song != null){
            Rate rateSong = new Rate(user, song, rate);
            rateService.save(rateSong);
            return ResponseEntity.ok().body(rateSong);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(path = "/rate/{id}")
    public ResponseEntity<Rate> delete(@PathVariable("id") Integer id) {
        rateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/rate")
    public ResponseEntity<Void> editRate(@RequestParam("id") int id, @RequestParam("rate") int rate){

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

}
