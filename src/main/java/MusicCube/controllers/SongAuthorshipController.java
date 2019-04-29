package MusicCube.controllers;

import MusicCube.entities.Person;
import MusicCube.entities.Song;
import MusicCube.services.songauthorship.SongAuthorshipService;
import MusicCube.entities.SongAuthorship;
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
public class SongAuthorshipController {

    @Autowired
    private SongAuthorshipService songAuthorshipService;

    @RequestMapping(value = "/songAuthorship{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<SongAuthorship> getById(int id) {
        return songAuthorshipService.getById(id);
    }

    @RequestMapping(value = "/songAuthorships",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SongAuthorship> getAll() {
        return songAuthorshipService.getAll();
    }


    // --- Get by Song ---
    @RequestMapping(value = "/songAuthors{song}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SongAuthorship> getBySong(Song song) { return songAuthorshipService.getBySong(song); }

    // --- Get by Author ---
    @RequestMapping(value = "/songAuthors{author}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SongAuthorship> getByAuthor(Person author) { return songAuthorshipService.getByAuthor(author); }


    @RequestMapping(value = "/songAuthorship",method = RequestMethod.POST)
    public ResponseEntity<SongAuthorship> create(@RequestBody @Valid @NotNull SongAuthorship songAuthorship) {
        songAuthorshipService.save(songAuthorship);
        return ResponseEntity.ok().body(songAuthorship);
    }

    @RequestMapping(value = "/songAuthorship",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull SongAuthorship songAuthorship) {
        Optional<SongAuthorship> songAuthor1 = songAuthorshipService.getById(songAuthorship.getId());
        if (Objects.nonNull(songAuthor1)) {
            songAuthorshipService.save(songAuthorship);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/songAuthors",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SongAuthorship> redirect(Model model) {
        return songAuthorshipService.getAll();
    }

    @RequestMapping(value = "/songAuthorship/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        songAuthorshipService.delete(id);
        return new RedirectView("/api/songAuthors",true);
    }
}
