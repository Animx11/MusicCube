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

    @RequestMapping(value = "/bandConcert{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<BandConcert> getById(int id) {
        return bandConcertService.getById(id);
    }

    @RequestMapping(value = "/bandConcerts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<BandConcert> getAll() {
        return bandConcertService.getAll();
    }

    // --- Get by Band ---
    @RequestMapping(value = "/bandConcerts{band}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<BandConcert> getByBand(Band band) { return bandConcertService.getByBand(band); }

    // --- Get by Concert ---
    @RequestMapping(value = "/bandConcerts{concert}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<BandConcert> getByConcert(Concert concert) { return bandConcertService.getByConcert(concert); }


    @RequestMapping(value = "/bandConcert",method = RequestMethod.POST)
    public ResponseEntity<BandConcert> create(@RequestBody @Valid @NotNull BandConcert bandConcert) {
        bandConcertService.save(bandConcert);
        return ResponseEntity.ok().body(bandConcert);
    }

    @RequestMapping(value = "/bandConcert",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull BandConcert bandConcert) {
        Optional<BandConcert> bandConcert1 = bandConcertService.getById(bandConcert.getId());
        if (Objects.nonNull(bandConcert1)) {
            bandConcertService.save(bandConcert);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/bandConcerts",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<BandConcert> redirect(Model model) {
        return bandConcertService.getAll();
    }

    @RequestMapping(value = "/bandConcert/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        bandConcertService.delete(id);
        return new RedirectView("/api/bandConcerts",true);
    }
}
