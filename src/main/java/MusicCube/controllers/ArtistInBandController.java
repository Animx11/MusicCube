package MusicCube.controllers;

import MusicCube.entities.Artist;
import MusicCube.entities.Band;
import MusicCube.services.artistinband.ArtistInBandService;
import MusicCube.entities.ArtistInBand;
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
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistInBandController {

    @Autowired
    private ArtistInBandService artistInBandService;

    @RequestMapping(value = "/artistInBand{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ArtistInBand> getById(int id) {
        return artistInBandService.getById(id);
    }

    @RequestMapping(value = "/artistInBands",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getAll() {
        return artistInBandService.getAll();
    }

    // --- Get by Artist ---
    @RequestMapping(value = "/artistInBands{artist}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getByArtist(Artist artist) { return artistInBandService.getByArtist(artist); }

    // --- Get by Band ---
    @RequestMapping(value = "/artistInBands{band}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getByBand(Band band) { return artistInBandService.getByBand(band); }

    @RequestMapping(value = "/artistInBand",method = RequestMethod.POST)
    public ResponseEntity<ArtistInBand> create(@RequestBody @Valid @NotNull ArtistInBand artistInBand) {
        artistInBandService.save(artistInBand);
        return ResponseEntity.ok().body(artistInBand);
    }

    @RequestMapping(value = "/artistInBand",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull ArtistInBand artistInBand) {
        Optional<ArtistInBand> artistInBand1 = artistInBandService.getById(artistInBand.getId());
        if (Objects.nonNull(artistInBand1)) {
            artistInBandService.save(artistInBand);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/artistInBands",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> redirect(Model model) {
        return artistInBandService.getAll();
    }

    @RequestMapping(value = "/artistInBand/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        artistInBandService.delete(id);
        return new RedirectView("/api/artistInBands",true);
    }



}
