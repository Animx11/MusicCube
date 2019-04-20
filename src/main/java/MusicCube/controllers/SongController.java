package MusicCube.controllers;

import MusicCube.services.song.SongService;
import MusicCube.entities.Song;
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
public class SongController {

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/song{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Song> getById(int id) {
        return songService.getById(id);
    }

    @RequestMapping(value = "/songs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> getAll() {
        return songService.getAll();
    }

    @RequestMapping(value = "/song",method = RequestMethod.POST)
    public ResponseEntity<Song> create(@RequestBody @Valid @NotNull Song song) {
        songService.save(song);
        return ResponseEntity.ok().body(song);
    }

    @RequestMapping(value = "/song",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Song song) {
        Optional<Song> song1 = songService.getById(song.getId());
        if (Objects.nonNull(song1)) {
            songService.save(song);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/songs",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> redirect(Model model) {
        return songService.getAll();
    }

    @RequestMapping(value = "/song/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        songService.delete(id);
        return new RedirectView("/api/songs",true);
    }
}
