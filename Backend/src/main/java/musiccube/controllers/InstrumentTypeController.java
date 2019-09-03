package musiccube.controllers;

import musiccube.entities.InstrumentType;
import musiccube.services.instrumentType.InstrumentTypeService;
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
public class InstrumentTypeController {

    @Autowired
    private InstrumentTypeService instrumentTypeService;

    @GetMapping(
            path = "/instrumenttype/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InstrumentType> getById(@PathVariable("id") int id) {
        Optional<InstrumentType> type = instrumentTypeService.getById(id);
        return type.isPresent() ?
                ResponseEntity.ok(type.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/instrumenttype",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<InstrumentType> getAll() {
        return instrumentTypeService.getAll();
    }

    @GetMapping(
            path = "instrumenttype/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<InstrumentType> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ){
        return perPage == null ?
                instrumentTypeService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                instrumentTypeService.getAllPaging(pageNr, perPage);
    }

    @GetMapping(
            path = "instrumenttype/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<InstrumentType> getByInstrumentTypeName(@PathVariable("name") String instrumentTypeName) {
        return instrumentTypeService.getByInstrumentTypeName(instrumentTypeName);
    }

    @PostMapping("/admin/instrumenttype")
    public ResponseEntity<InstrumentType> create(@RequestBody @Valid @NotNull InstrumentType type) {
        instrumentTypeService.save(type);
        return ResponseEntity.ok(type);
    }

    @PutMapping("/admin/instrumenttype")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull InstrumentType instrumentType) {
        Optional<InstrumentType> type = instrumentTypeService.getById(instrumentType.getId());
        if (Objects.nonNull(type)) {
            instrumentTypeService.save(instrumentType);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/admin/instrumenttype/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        instrumentTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
