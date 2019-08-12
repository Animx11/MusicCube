package musiccube.controllers;

import musiccube.services.genre.GenreService;
import musiccube.entities.Genre;
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
public class GenreController {

    @Autowired
    private GenreService genreService;

    @RequestMapping(value = "/genre{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Genre> getById(int id) {
        return genreService.getById(id);
    }

    @RequestMapping(value = "/genres",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> getAll() {
        return genreService.getAll();
    }

    // --- Get all genres with paging ---
    @RequestMapping(value = "genres/{page}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> perPage) {
        return genreService.getAllPaging(pageNr,perPage.orElse(10));
    }

    @RequestMapping(value = "/genres{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> getByGenreName(String genreName) {
        return genreService.getByGenreName(genreName);
    }

    @RequestMapping(value = "/genre",method = RequestMethod.POST)
    public ResponseEntity<Genre> create(@RequestBody @Valid @NotNull Genre genre) {
        genreService.save(genre);
        return ResponseEntity.ok().body(genre);
    }

    @RequestMapping(value = "/genre",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Genre genre) {
        Optional<Genre> genre1 = genreService.getById(genre.getId());
        if (Objects.nonNull(genre1)) {
            genreService.save(genre);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/genres",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> redirect(Model model) {
        return genreService.getAll();
    }

    @RequestMapping(value = "/genre/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        genreService.delete(id);
        return new RedirectView("/api/genres",true);
    }
}
