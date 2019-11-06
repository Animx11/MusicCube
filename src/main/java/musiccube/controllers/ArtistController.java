package musiccube.controllers;

import musiccube.entities.Artist;
import musiccube.entities.Person;
import musiccube.services.artist.ArtistService;
import musiccube.services.person.PersonService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class ArtistController {
    
    @Autowired
    private ArtistService artistService;
    @Autowired
    private PersonService personService;

    @GetMapping(
            path = "/artist/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artist> getById(@PathVariable("id") int id) {
        Optional<Artist> artist = artistService.getById(id);
        return artist.isPresent() ?
                ResponseEntity.ok(artist.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path= "/artist",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> getAll() {
        return artistService.getAll();
    }

    // --- Get all artists with paging ---
    @GetMapping(
            path = "artist/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Artist> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {
        return perPage == null ?
                artistService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                artistService.getAllPaging(pageNr, perPage);
    }

    // --- GET BY STAGE NAME ---
    @GetMapping(path = "/artist/stagename/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> getByStageName(@PathVariable("name") String name) {
        return artistService.getByStageName(name);
    }

    // --- GET BY LASTNAME ---
    @GetMapping(
            path = "/artist/lastname/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> getByLastName(@PathVariable("name") String lastName) {
        ArrayList<Person> persons = (ArrayList<Person>) personService.getByLastName(lastName);
        return persons.stream()
                .filter(person -> person instanceof Artist)
                .map(Artist.class::cast)
                .collect(Collectors.toList());
    }
    // --- GET BY ANYTHING ---
    @GetMapping(
            path = "/artist/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Artist> getByAnything(@PathVariable("name") String input) {
        return artistService.getByAnything(input);
    }

    // --- ADVANCED SEARCH ---
    @GetMapping(
            path = "/artist/advanced",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Artist> getAdvanced(
            @RequestParam("band") Optional<Integer> band,
            @RequestParam("country") Optional<Integer> country,
            @RequestParam("city") Optional<Integer> city,
            @RequestParam("instrument") Optional<Integer> instrument
    ) {
        return artistService.advancedSearch(band, country, city, instrument);
    }

    @PostMapping("/admin/artist")
    public ResponseEntity<Artist> create(@RequestBody @Valid @NotNull Artist artist) {
        artistService.save(artist);
        return ResponseEntity.ok().body(artist);
    }

    @PutMapping("/admin/artist")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Artist artist) {
        Optional<Artist> artist1 = artistService.getById(artist.getId());
        if (Objects.nonNull(artist1)) {
            artistService.save(artist);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/artist/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        artistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
