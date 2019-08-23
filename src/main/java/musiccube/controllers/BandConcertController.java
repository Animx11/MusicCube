package musiccube.controllers;

import musiccube.entities.Band;
import musiccube.entities.Concert;
import musiccube.services.bandconcert.BandConcertService;
import musiccube.entities.BandConcert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class BandConcertController {

    @Autowired
    private BandConcertService bandConcertService;

    @GetMapping(
            path = "/bandconcert/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BandConcert> getById(int id) {
        Optional<BandConcert> concert =  bandConcertService.getById(id);
        return concert.isPresent() ?
                ResponseEntity.ok(concert.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/bandconcert",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<BandConcert> getAll() {
        return bandConcertService.getAll();
    }

    // --- Get by Band ---
    @GetMapping(
            path = "/bandconcert/band/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<BandConcert> getByBand(@PathVariable("id") int bandId) {
        return bandConcertService.getByBandId(bandId);
    }

    // --- Get by Concert ---
    @GetMapping(
            path = "/bandconcert/concert/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<BandConcert> getByConcert(@PathVariable("id") int concertId) {
        return bandConcertService.getByConcertId(concertId);
    }


    @PostMapping("/admin/bandconcert")
    public ResponseEntity<BandConcert> create(@RequestBody @Valid @NotNull BandConcert bandConcert) {
        bandConcertService.save(bandConcert);
        return ResponseEntity.ok().body(bandConcert);
    }

    @PutMapping("/admin/bandconcert")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull BandConcert bandConcert) {
        Optional<BandConcert> bandConcert1 = bandConcertService.getById(bandConcert.getId());
        if (Objects.nonNull(bandConcert1)) {
            bandConcertService.save(bandConcert);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @DeleteMapping(
            path = "/admin/bandconcert",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<BandConcert> redirect(Model model) {
        return bandConcertService.getAll();
    }

    @DeleteMapping("/admin/bandconcert/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        bandConcertService.delete(id);
        return new RedirectView("/api/admin/bandconcert",true);
    }
}
