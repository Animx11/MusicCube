package MusicCube.controllers;

import MusicCube.services.songauthor.SongAuthorService;
import MusicCube.entities.SongAuthor;
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
public class SongAuthorController {

    @Autowired
    private SongAuthorService songAuthorService;

    @RequestMapping(value = "/songAuthor{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<SongAuthor> getById(int id) {
        return songAuthorService.getById(id);
    }

    @RequestMapping(value = "/songAuthors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SongAuthor> getAll() {
        return songAuthorService.getAll();
    }

    @RequestMapping(value = "/songAuthor",method = RequestMethod.POST)
    public ResponseEntity<SongAuthor> create(@RequestBody @Valid @NotNull SongAuthor songAuthor) {
        songAuthorService.save(songAuthor);
        return ResponseEntity.ok().body(songAuthor);
    }

    @RequestMapping(value = "/songAuthor",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull SongAuthor songAuthor) {
        Optional<SongAuthor> songAuthor1 = songAuthorService.getById(songAuthor.getId());
        if (Objects.nonNull(songAuthor1)) {
            songAuthorService.save(songAuthor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/songAuthors",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<SongAuthor> redirect(Model model) {
        return songAuthorService.getAll();
    }

    @RequestMapping(value = "/songAuthor/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        songAuthorService.delete(id);
        return new RedirectView("/api/songAuthors",true);
    }
}
