package musiccube.controllers;

import musiccube.entities.Genre;
import musiccube.services.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping(
            path = "/genre/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Genre> getById(@PathVariable("id") int id) {
        Optional<Genre> genre = genreService.getById(id);
        return genre.isPresent() ?
                ResponseEntity.ok(genre.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/genre",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Genre> getAll() {
        return genreService.getAll();
    }

    // --- Get all genres with paging ---
    @GetMapping(
            path = "genre/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Genre> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {
        return perPage == null ?
                genreService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                genreService.getAllPaging(pageNr, perPage);
    }

    // --- Get by name ---
    @GetMapping(
            path = "/genre/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> getByGenreName(@PathVariable("name") String genreName) {
        return genreService.getByGenreName(genreName);
    }

    @PostMapping("/admin/genre")
    public ResponseEntity<Genre> create(@RequestBody @Valid @NotNull Genre genre) {
        genreService.save(genre);
        return ResponseEntity.ok().body(genre);
    }

    @PutMapping("/admin/genre")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Genre genre) {
        Optional<Genre> genre1 = genreService.getById(genre.getId());
        if (Objects.nonNull(genre1)) {
            genreService.save(genre);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/genre/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        genreService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
