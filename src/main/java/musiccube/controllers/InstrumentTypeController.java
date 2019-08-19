package musiccube.controllers;



import musiccube.entities.InstrumentType;
import musiccube.services.instrumentType.InstrumentTypeService;
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
@CrossOrigin(origins = "${serverAddress}")
public class InstrumentTypeController {

    @Autowired
    private InstrumentTypeService instrumentTypeService;

    @GetMapping(value = "/instrumentType{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<InstrumentType> getById(int id) {
        return instrumentTypeService.getById(id);
    }

    @GetMapping(value = "/instrumentTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<InstrumentType> getAll() {
        return instrumentTypeService.getAll();
    }

    @GetMapping(value = "instrumentType/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<InstrumentType> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> perPage){
        return instrumentTypeService.getAllPaging(pageNr, perPage.orElse(10));
    }

    @GetMapping(value = "instrumentTypes{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<InstrumentType> getByInstrumentTypeName(String instrumentTypeName) {
        return instrumentTypeService.getByInstrumentTypeName(instrumentTypeName);
    }

    @PutMapping(value = "/instrumentType")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull InstrumentType instrumentType) {
        Optional<InstrumentType> type = instrumentTypeService.getById(instrumentType.getId());
        if (Objects.nonNull(type)) {
            instrumentTypeService.save(instrumentType);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/instrumentType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<InstrumentType> redirect(Model model) {
        return instrumentTypeService.getAll();
    }

    @DeleteMapping(value = "/instrumentType/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        instrumentTypeService.delete(id);
        return new RedirectView("/api/instrumentType",true);
    }

}
