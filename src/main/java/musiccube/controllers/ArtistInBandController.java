package musiccube.controllers;

import musiccube.services.artistinband.ArtistInBandService;
import musiccube.entities.ArtistInBand;
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
public class ArtistInBandController {

    @Autowired
    private ArtistInBandService artistInBandService;

/*************************** GET ***********************************/
    @GetMapping(value = "/artistInBand{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ArtistInBand> getById(int id) {
        return artistInBandService.getById(id);
    }

    @GetMapping(value = "/artistInBands",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getAll() {
        return artistInBandService.getAll();
    }

    // --- Get by Artist ---
    @RequestMapping(value = "/artistInBands{artist}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getByArtist(int artistId) { return artistInBandService.getByArtistId(artistId); }

    // --- Get by Band ---
    @RequestMapping(value = "/artistInBands{band}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getByBand(int bandId) { return artistInBandService.getByBandId(bandId); }

    @RequestMapping(value = "/artistInBands{artist,active}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getByArtistIdIsActive(int artistId, boolean active) {
        return artistInBandService.getByArtistIdIsActive(artistId,active);
    }
    @RequestMapping(value = "/artistInBands{band,active}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> getByBandIdIsActive(int bandId, boolean active) {
        return artistInBandService.getByBandIdIsActive(bandId,active);
    }
/************************************************************************************/

    @PostMapping(value = "/artistInBand")
    public ResponseEntity<ArtistInBand> create(@RequestBody @Valid @NotNull ArtistInBand artistInBand) {
        artistInBandService.save(artistInBand);
        return ResponseEntity.ok().body(artistInBand);
    }

    @PutMapping(value = "/artistInBand")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull ArtistInBand artistInBand) {
        Optional<ArtistInBand> artistInBand1 = artistInBandService.getById(artistInBand.getId());
        if (Objects.nonNull(artistInBand1)) {
            artistInBandService.save(artistInBand);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @DeleteMapping(value = "/artistInBands",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInBand> redirect(Model model) {
        return artistInBandService.getAll();
    }

    @DeleteMapping(value = "/artistInBand/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        artistInBandService.delete(id);
        return new RedirectView("/api/artistInBands",true);
    }



}
