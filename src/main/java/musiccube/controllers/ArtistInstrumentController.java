package musiccube.controllers;

import musiccube.entities.Artist;
import musiccube.entities.Instrument;
import musiccube.services.artistinstrument.ArtistInstrumentService;
import musiccube.entities.ArtistInstrument;
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
public class ArtistInstrumentController {

    @Autowired
    private ArtistInstrumentService artistInstrumentService;

    @RequestMapping(value = "/artistInstrument{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ArtistInstrument> getById(int id) {
        return artistInstrumentService.getById(id);
    }

    @RequestMapping(value = "/artistInstruments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInstrument> getAll() {
        return artistInstrumentService.getAll();
    }

    // --- Get by Artist ---
    @RequestMapping(value = "/artistInstruments{artist}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInstrument> getByArtist(Artist artist) { return artistInstrumentService.getByArtist(artist); }

    // --- Get by Instrument ---
    @RequestMapping(value = "/artistInstruments{instrument}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInstrument> getByInstrument(Instrument instrument) { return artistInstrumentService.getByInstrument(instrument); }

    @RequestMapping(value = "/artistInstrument",method = RequestMethod.POST)
    public ResponseEntity<ArtistInstrument> create(@RequestBody @Valid @NotNull ArtistInstrument artistInstrument) {
        artistInstrumentService.save(artistInstrument);
        return ResponseEntity.ok().body(artistInstrument);
    }

    @RequestMapping(value = "/artistInstrument",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull ArtistInstrument artistInstrument) {
        Optional<ArtistInstrument> artistInstrument1 = artistInstrumentService.getById(artistInstrument.getId());
        if (Objects.nonNull(artistInstrument1)) {
            artistInstrumentService.save(artistInstrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/artistInstruments",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ArtistInstrument> redirect(Model model) {
        return artistInstrumentService.getAll();
    }

    @RequestMapping(value = "/artistInstrument/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        artistInstrumentService.delete(id);
        return new RedirectView("/api/artistInstruments",true);
    }
}
