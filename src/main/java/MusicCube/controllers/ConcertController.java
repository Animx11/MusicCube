package MusicCube.controllers;

import MusicCube.entities.Concert;
import MusicCube.services.concert.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")

public class ConcertController {
    @Autowired
    private ConcertService concertService;

    @RequestMapping(value = "/concerts{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Concert> getById(int id) {
        return concertService.getById(id);
    }

    @RequestMapping(value = "/concerts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Concert> getAll() {
        return concertService.getAll();
    }

    @RequestMapping(value = "/concerts",method = RequestMethod.POST)
    public ResponseEntity<Concert> create(@RequestBody @Valid @NotNull Concert concert) {
        concertService.save(concert);
        return ResponseEntity.ok().body(concert);
    }

    @RequestMapping(value = "/concerts",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Concert concert) {
        Optional<Concert> artist1 = concertService.getById(concert.getId());
        if (Objects.nonNull(artist1)) {
            concertService.save(concert);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/concerts",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Concert> redirect(Model model) {
        return concertService.getAll();
    }

    @RequestMapping(value = "/concerts/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        concertService.delete(id);
        return new RedirectView("/api/concerts",true);
    }

    @RequestMapping(value = "/Concert{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Concert> getByName(String name) {
        return concertService.getByName(name);
    }
}
