package MusicCube.controllers;

import MusicCube.entities.Genre;
import MusicCube.entities.Instrument;
import MusicCube.services.genreinstrument.GenreInstrumentService;
import MusicCube.entities.GenreInstrument;
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
public class GenreInstrumentController {

    @Autowired
    private GenreInstrumentService genreInstrumentService;

    @RequestMapping(value = "/genreInstrument{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<GenreInstrument> getById(int id) {
        return genreInstrumentService.getById(id);
    }

    @RequestMapping(value = "/genreInstruments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<GenreInstrument> getAll() {
        return genreInstrumentService.getAll();
    }

    // --- Get by Genre ---
    @RequestMapping(value = "/genreInstruments{genre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<GenreInstrument> getByGenre(Genre genre) { return genreInstrumentService.getByGenre(genre); }

    // --- Get by Instrument ---
    @RequestMapping(value = "/genreInstruments{instrument}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<GenreInstrument> getByInstrument(Instrument instrument) { return genreInstrumentService.getByInstrument(instrument); }


    @RequestMapping(value = "/genreInstrument",method = RequestMethod.POST)
    public ResponseEntity<GenreInstrument> create(@RequestBody @Valid @NotNull GenreInstrument genreInstrument) {
        genreInstrumentService.save(genreInstrument);
        return ResponseEntity.ok().body(genreInstrument);
    }

    @RequestMapping(value = "/genreInstrument",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull GenreInstrument genreInstrument) {
        Optional<GenreInstrument> genreInstrument1 = genreInstrumentService.getById(genreInstrument.getId());
        if (Objects.nonNull(genreInstrument1)) {
            genreInstrumentService.save(genreInstrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/genreInstruments",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<GenreInstrument> redirect(Model model) {
        return genreInstrumentService.getAll();
    }

    @RequestMapping(value = "/genreInstrument/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        genreInstrumentService.delete(id);
        return new RedirectView("/api/genreInstruments",true);
    }
}
