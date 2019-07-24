package musiccube.controllers;

import musiccube.entities.Instrument;
import musiccube.services.instrument.InstrumentService;
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
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    @RequestMapping(value = "/instrument{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Instrument> getById(int id) {
        return instrumentService.getById(id);
    }

    @RequestMapping(value = "/instruments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instrument> getAll() {
        return instrumentService.getAll();
    }
    
    // --- Get all instruments with paging ---
    @RequestMapping(value = "instruments/{page}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instrument> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> perPage) {
        return instrumentService.getAllPaging(pageNr,perPage.orElse(10));
    }

    // --- Get by instrument name ---
    @RequestMapping(value = "/instruments{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instrument> getByInstrumentName(String instrumentName) { return instrumentService.getByInstrumentName(instrumentName); }

    @RequestMapping(value = "/instrument",method = RequestMethod.POST)
    public ResponseEntity<Instrument> create(@RequestBody @Valid @NotNull Instrument instrument) {
        instrumentService.save(instrument);
        return ResponseEntity.ok().body(instrument);
    }

    @RequestMapping(value = "/instrument",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Instrument instrument) {
        Optional<Instrument> artist1 = instrumentService.getById(instrument.getId());
        if (Objects.nonNull(artist1)) {
            instrumentService.save(instrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/instrument",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instrument> redirect(Model model) {
        return instrumentService.getAll();
    }

    @RequestMapping(value = "/instrument/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        instrumentService.delete(id);
        return new RedirectView("/api/instrument",true);
    }
}
