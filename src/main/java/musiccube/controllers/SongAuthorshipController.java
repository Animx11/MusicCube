package musiccube.controllers;

import musiccube.entities.SongAuthorship;
import musiccube.services.songauthorship.SongAuthorshipService;
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
public class SongAuthorshipController {

    @Autowired
    private SongAuthorshipService songAuthorshipService;

    @GetMapping(
            path = "/songauthorship/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SongAuthorship> getById(@PathVariable("id") int id) {
        Optional<SongAuthorship> authorship =  songAuthorshipService.getById(id);
        return authorship.isPresent() ?
                ResponseEntity.ok(authorship.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/songauthorship",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<SongAuthorship> getAll() {
        return songAuthorshipService.getAll();
    }


    // --- Get by Song ---
    @GetMapping(
            path = "/songauthorship/song/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<SongAuthorship> getBySong(@PathVariable("id") int id) {
        return songAuthorshipService.getBySongId(id);
    }

    // --- Get by Author ---
    @GetMapping(
            path = "/songauthorship/author/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<SongAuthorship> getByAuthor(@PathVariable("id") int id) {
        return songAuthorshipService.getByAuthorId(id);
    }


    @PostMapping("/admin/songauthorship")
    public ResponseEntity<SongAuthorship> create(@RequestBody @Valid @NotNull SongAuthorship songAuthorship) {
        songAuthorshipService.save(songAuthorship);
        return ResponseEntity.ok().body(songAuthorship);
    }

    @PutMapping("/admin/songauthorship")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull SongAuthorship songAuthorship) {
        Optional<SongAuthorship> songAuthor1 = songAuthorshipService.getById(songAuthorship.getId());
        if (Objects.nonNull(songAuthor1)) {
            songAuthorshipService.save(songAuthorship);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/songauthorship/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        songAuthorshipService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
