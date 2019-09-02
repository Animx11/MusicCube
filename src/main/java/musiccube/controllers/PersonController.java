package musiccube.controllers;

import musiccube.entities.*;
import musiccube.services.artist.ArtistService;
import musiccube.services.artistactivity.ArtistActivityService;
import musiccube.services.artistinstrument.ArtistInstrumentService;
import musiccube.services.comment.CommentService;
import musiccube.services.person.PersonService;
import musiccube.services.rate.RateService;
import musiccube.services.songauthorship.SongAuthorshipService;
import musiccube.services.userFavorites.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistActivityService artistActivityService;

    @Autowired
    private ArtistInstrumentService artistInstrumentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RateService rateService;

    @Autowired
    private UserFavoritesService userFavoritesService;

    @Autowired
    private SongAuthorshipService songAuthorshipService;

    @GetMapping(
            path = "/person/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Person> getById(@PathVariable("id") int id) {
        Optional<Person> person = personService.getById(id);
        return person.isPresent() ?
                ResponseEntity.ok(person.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/person",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Person> getAll() {
        return personService.getAll();
    }

    // --- Get all persons with paging ---
    @GetMapping(
            path = "person/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Person> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage) {
        return perPage == null ?
                personService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                personService.getAllPaging(pageNr, perPage);
    }

    // --- GET BY LAST NAME ---
    @GetMapping(
            path = "/person/lastname/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Person> getByLastName(@PathVariable("name") String lastName) {
        return personService.getByLastName(lastName);
    }

    @GetMapping(
            path = "/person/anything",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Artist> getByAnything(@RequestParam("input") String input) {
        return personService.getByAnything(input);
    }

    @PostMapping("/admin/person")
    public ResponseEntity<Person> create(@RequestBody @Valid @NotNull Person person) {
        personService.save(person);
        return ResponseEntity.ok().body(person);
    }

    @PutMapping("/admin/person")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Person person) {
        Optional<Person> person1 = personService.getById(person.getId());
        if (Objects.nonNull(person1)) {
            personService.save(person);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/person/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Person person = personService.getById(id).orElse(null);
        if(person != null){
            Artist artist = artistService.getById(id).orElse(null);
            if(artist != null) {
                Iterable<ArtistActivity> allArtistActivities = artistActivityService.getByArtistId(artist.getId());
                for(ArtistActivity artistActivity : allArtistActivities){
                    artistActivityService.delete(artistActivity.getId());
                }
                Iterable<ArtistInstrument> allArtistInstruments = artistInstrumentService.getByArtistId(artist.getId());
                for(ArtistInstrument artistInstrument : allArtistInstruments){
                    artistInstrumentService.delete(artistInstrument.getId());
                }
                Iterable<Comment> allArtistComments = commentService.getCommentsByArtistId(artist.getId());
                for (Comment comment : allArtistComments) {
                    commentService.delete(comment.getId());
                }
                Iterable<Rate> allArtistRates = rateService.getRatesByArtistId(artist.getId());
                for (Rate rate : allArtistRates) {
                    rateService.delete(rate.getId());
                }
                Iterable<UserFavorites> allUserFavoritesLists = userFavoritesService.getAll();
                for (UserFavorites userFavorites : allUserFavoritesLists) {
                    Set<Artist> userFavoritesSongs = userFavorites.getFavoriteArtists();
                    if(userFavoritesSongs.contains(artist)) {
                        userFavorites.deleteArtistFromFavorites(artist);
                    }
                }
            }
            Iterable<SongAuthorship> allPersonSongAuthorships = songAuthorshipService.getByAuthorId(person.getId());
            for (SongAuthorship songAuthorship : allPersonSongAuthorships) {
                songAuthorshipService.delete(songAuthorship.getId());
            }
            personService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
