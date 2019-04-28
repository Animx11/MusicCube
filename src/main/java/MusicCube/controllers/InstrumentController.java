package MusicCube.Controllers;

import MusicCube.Entities.Instruments;
import MusicCube.Services.InstrumentService;
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
public class InstrumentsController {
    @Autowired
    private InstrumentService instrumentService;

    @RequestMapping(value = "/instruments{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Instruments> getById(int id) {
        return instrumentService.getById(id);
    }

    @RequestMapping(value = "/instruments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instruments> getAll() {
        return instrumentService.getAll();
    }

    @RequestMapping(value = "/instruments",method = RequestMethod.POST)
    public ResponseEntity<Instruments> create(@RequestBody @Valid @NotNull Instruments instrument) {
        instrumentService.save(instrument);
        return ResponseEntity.ok().body(instrument);
    }

    @RequestMapping(value = "/instruments",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Instruments instrument) {
        Optional<Instruments> artist1 = instrumentService.getById(instrument.getId());
        if (Objects.nonNull(artist1)) {
            instrumentService.save(instrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/instruments",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Instruments> redirect(Model model) {
        return instrumentService.getAll();
    }

    @RequestMapping(value = "/instruments/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        instrumentService.delete(id);
        return new RedirectView("/api/instruments",true);
    }
}
