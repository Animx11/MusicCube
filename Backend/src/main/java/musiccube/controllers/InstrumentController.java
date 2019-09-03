package musiccube.controllers;

import musiccube.entities.Instrument;
import musiccube.services.instrument.InstrumentService;
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
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    @GetMapping(
            path = "/instrument/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Instrument> getById(@PathVariable("id") int id) {
        Optional<Instrument> instrument = instrumentService.getById(id);
        return instrument.isPresent() ?
                ResponseEntity.ok(instrument.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/instrument",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Instrument> getAll() {
        return instrumentService.getAll();
    }
    
    // --- Get all instruments with paging ---
    @GetMapping(
            path = "instrument/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Instrument> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {
        return perPage == null ?
                instrumentService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                instrumentService.getAllPaging(pageNr, perPage);
    }

    // --- Get by instrument name ---
    @GetMapping(
            path = "/instrument/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Instrument> getByInstrumentName(@PathVariable("name") String instrumentName) {
        return instrumentService.getByInstrumentName(instrumentName);
    }

    @PostMapping("/admin/instrument")
    public ResponseEntity<Instrument> create(@RequestBody @Valid @NotNull Instrument instrument) {
        instrumentService.save(instrument);
        return ResponseEntity.ok().body(instrument);
    }

    @PutMapping("/admin/instrument")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Instrument instrument) {
        Optional<Instrument> artist1 = instrumentService.getById(instrument.getId());
        if (Objects.nonNull(artist1)) {
            instrumentService.save(instrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/instrument/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        instrumentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
