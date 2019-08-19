package musiccube.controllers;

import musiccube.services.song.SongService;
import musiccube.entities.Song;
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
@CrossOrigin(origins = "${serverAddress}")
public class SongController {

    @Autowired
    private SongService songService;

    // --- Get by id ---
    @RequestMapping(value = "/song{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Song> getById(int id) {
        return songService.getById(id);
    }

    // --- Get all songs ---
    @RequestMapping(value = "/songs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> getAll() {
        return songService.getAll();
    }

    // --- Get all songs with paging ---
    @RequestMapping(value = "songs/{page}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> perPage) {
        return songService.getAllPaging(pageNr,perPage.orElse(10));
    }

    // --- Get by song name ---
    @RequestMapping(value = "/songs{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> getBySongName(String songName) {
        return songService.getBySongName(songName);
    }

/**********************************************************************************/

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
