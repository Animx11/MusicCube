package musiccube.controllers;

import musiccube.entities.ArtistInstrument;
import musiccube.services.artistinstrument.ArtistInstrumentService;
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
public class ArtistInstrumentController {

    @Autowired
    private ArtistInstrumentService artistinstrumentService;

    @GetMapping(
            path = "/artistinstrument/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArtistInstrument> getById(@PathVariable("id") int id) {
        Optional<ArtistInstrument> ai = artistinstrumentService.getById(id);
        return ai.isPresent() ?
                ResponseEntity.ok(ai.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/artistinstrument",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<ArtistInstrument> getAll() {
        return artistinstrumentService.getAll();
    }

    // --- Get by Artist ---
    @GetMapping(
            path = "/artistinstrument/artist/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<ArtistInstrument> getByArtist(@PathVariable("id") int artistId) {
        return artistinstrumentService.getByArtistId(artistId);
    }

    // --- Get by Instrument ---
    @GetMapping(
            path = "/artistinstrument/instrument/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<ArtistInstrument> getByInstrument(@PathVariable("id") int instrumentId) {
        return artistinstrumentService.getByInstrumentId(instrumentId);
    }

    @PostMapping("/admin/artistinstrument")
    public ResponseEntity<ArtistInstrument> create(@RequestBody @Valid @NotNull ArtistInstrument artistinstrument) {
        artistinstrumentService.save(artistinstrument);
        return ResponseEntity.ok().body(artistinstrument);
    }

    @PutMapping("/admin/artistinstrument")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull ArtistInstrument artistinstrument) {
        Optional<ArtistInstrument> artistinstrument1 = artistinstrumentService.getById(artistinstrument.getId());
        if (Objects.nonNull(artistinstrument1)) {
            artistinstrumentService.save(artistinstrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/artistinstrument/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        artistinstrumentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
