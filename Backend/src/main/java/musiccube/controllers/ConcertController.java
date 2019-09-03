package musiccube.controllers;

import musiccube.entities.Concert;
import musiccube.services.concert.ConcertService;
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

public class ConcertController {
    @Autowired
    private ConcertService concertService;

    @GetMapping(
            path = "/concert/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Concert> getById(@PathVariable("id") int id) {
        Optional<Concert> concert = concertService.getById(id);
        return concert.isPresent() ?
                ResponseEntity.ok(concert.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/concert",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Concert> getAll() {
        return concertService.getAll();
    }

    // --- Get by concertName
    @GetMapping(path = "/concert/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Concert> getByConcertName(@PathVariable("name") String concertName) {
        return concertService.getByConcertName(concertName);
    }

    @PostMapping("/admin/concert")
    public ResponseEntity<Concert> create(@RequestBody @Valid @NotNull Concert concert) {
        concertService.save(concert);
        return ResponseEntity.ok().body(concert);
    }

    @PutMapping("/admin/concert")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Concert concert) {
        Optional<Concert> artist1 = concertService.getById(concert.getId());
        if (Objects.nonNull(artist1)) {
            concertService.save(concert);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/concert/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        concertService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
