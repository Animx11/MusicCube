package musiccube.controllers;

import musiccube.entities.SongInstrument;
import musiccube.services.songinstrument.SongInstrumentService;
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
public class SongInstrumentController {

    @Autowired
    private SongInstrumentService songInstrumentService;

    @GetMapping(path = "/songinstrument/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<SongInstrument> getById(@PathVariable("id") int id) {
        return songInstrumentService.getById(id);
    }

    @GetMapping(
            path = "/songinstrument",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<SongInstrument> getAll() {
        return songInstrumentService.getAll();
    }

    // --- Get by Song ---
    @GetMapping(
            path = "/songinstrument/song/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<SongInstrument> getBySong(@PathVariable("id") int id) {
        return songInstrumentService.getBySongId(id);
    }

    // --- Get by Instrument ---
    @GetMapping(
            path = "/songinstrument/instrument/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<SongInstrument> getByInstrument(@PathVariable("id") int id) {
        return songInstrumentService.getByInstrumentId(id);
    }


    @PostMapping("/admin/songinstrument")
    public ResponseEntity<SongInstrument> create(@RequestBody @Valid @NotNull SongInstrument songInstrument) {
        songInstrumentService.save(songInstrument);
        return ResponseEntity.ok().body(songInstrument);
    }

    @PutMapping("/admin/songinstrument")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull SongInstrument songInstrument) {
        Optional<SongInstrument> songInstrument1 = songInstrumentService.getById(songInstrument.getId());
        if (Objects.nonNull(songInstrument1)) {
            songInstrumentService.save(songInstrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/songinstrument/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        songInstrumentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
