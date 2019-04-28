package MusicCube.controllers;

import MusicCube.services.artist.ArtistService;
import MusicCube.entities.Artist;
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
public class ArtistController {
    
    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/artist{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Artist> getById(int id) {
        return artistService.getById(id);
    }

    @RequestMapping(value = "/artists",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> getAll() {
        return artistService.getAll();
    }

    // --- GET BY NAME ---
    @RequestMapping(value = "/artists{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> getByStageName(String name) {
        return artistService.getByStageName(name);
    }

    @RequestMapping(value = "/artist",method = RequestMethod.POST)
    public ResponseEntity<Artist> create(@RequestBody @Valid @NotNull Artist artist) {
        artistService.save(artist);
        return ResponseEntity.ok().body(artist);
    }

    @RequestMapping(value = "/artist",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Artist artist) {
        Optional<Artist> artist1 = artistService.getById(artist.getId());
        if (Objects.nonNull(artist1)) {
            artistService.save(artist);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/artists",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> redirect(Model model) {
        return artistService.getAll();
    }

    @RequestMapping(value = "/artist/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        artistService.delete(id);
        return new RedirectView("/api/artists",true);
    }
}
