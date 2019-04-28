package MusicCube.controllers;

import MusicCube.entities.Instrument;
import MusicCube.services.instrument.InstrumentService;
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
    private InstrumentService instrumentervice;

    @RequestMapping(value = "/instrument{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Instrument> getById(int id) {
        return instrumentervice.getById(id);
    }

    @RequestMapping(value = "/instruments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instrument> getAll() {
        return instrumentervice.getAll();
    }

    @RequestMapping(value = "/instrument",method = RequestMethod.POST)
    public ResponseEntity<Instrument> create(@RequestBody @Valid @NotNull Instrument instrument) {
        instrumentervice.save(instrument);
        return ResponseEntity.ok().body(instrument);
    }

    @RequestMapping(value = "/instrument",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Instrument instrument) {
        Optional<Instrument> artist1 = instrumentervice.getById(instrument.getId());
        if (Objects.nonNull(artist1)) {
            instrumentervice.save(instrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/instrument",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instrument> redirect(Model model) {
        return instrumentervice.getAll();
    }

    @RequestMapping(value = "/instrument/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        instrumentervice.delete(id);
        return new RedirectView("/api/instrument",true);
    }
}
