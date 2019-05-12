package MusicCube.controllers;

import MusicCube.entities.Genre;
import MusicCube.services.subgenre.SubgenreService;
import MusicCube.entities.Subgenre;
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
public class SubgenreController {

    @Autowired
    private SubgenreService subgenreService;

    @RequestMapping(value = "/subgenre{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Subgenre> getById(int id) {
        return subgenreService.getById(id);
    }

    @RequestMapping(value = "/subgenres",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Subgenre> getAll() {
        return subgenreService.getAll();
    }
    
    // --- Get by child genre ---
    @RequestMapping(value = "/subgenres{childgenre}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Subgenre> getByChildGenre(Genre childGenre) { return subgenreService.getByChildGenre(childGenre); }
    // --- Get by parent genre ---
    @RequestMapping(value = "/subgenres{parent}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Subgenre> getByParentGenre(Genre parentGenre) { return subgenreService.getByParentGenre(parentGenre); }

    @RequestMapping(value = "/subgenre",method = RequestMethod.POST)
    public ResponseEntity<Subgenre> create(@RequestBody @Valid @NotNull Subgenre subgenre) {
        subgenreService.save(subgenre);
        return ResponseEntity.ok().body(subgenre);
    }

    @RequestMapping(value = "/subgenre",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Subgenre subgenre) {
        Optional<Subgenre> subgenre1 = subgenreService.getById(subgenre.getId());
        if (Objects.nonNull(subgenre1)) {
            subgenreService.save(subgenre);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/subgenres",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Subgenre> redirect(Model model) {
        return subgenreService.getAll();
    }

    @RequestMapping(value = "/subgenre/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        subgenreService.delete(id);
        return new RedirectView("/api/subgenres",true);
    }
}
