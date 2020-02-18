package musiccube.controllers;

import musiccube.entities.*;
import musiccube.services.artistactivity.ArtistActivityService;
import musiccube.services.band.BandService;
import musiccube.services.bandconcert.BandConcertService;
import musiccube.services.comment.CommentService;
import musiccube.services.rate.RateService;
import musiccube.services.song.SongService;
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
public class BandController {

    @Autowired
    private BandService bandService;

    @Autowired
    private ArtistActivityService artistActivityService;

    @Autowired
    private BandConcertService bandConcertService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RateService rateService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserFavoritesService userFavoritesService;

/******************************** GET ***************************************/

    @GetMapping(
            path = "/band/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Band> getById(@PathVariable("id") int id) {
        Optional<Band> band = bandService.getById(id);
        return band.isPresent() ?
                ResponseEntity.ok(band.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/band",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getAll() {
        return bandService.getAll();
    }

    // --- Get all bands with paging ---
    @GetMapping(
            path = "band/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {
        return perPage == null ?
                bandService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                bandService.getAllPaging(pageNr, perPage);
    }

    // --- Band by name ---
    @GetMapping(
            path = "/band/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getByBandName(@PathVariable("name") String bandName) {
        return bandService.getByBandName(bandName);
    }

    // --- Genres ---
    @GetMapping(
            path = "/band/{id}/genres",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Genre> getBandGenres(@PathVariable("id") int bandId) {
        return bandService.getBandGenres(bandId);
    }

    // --- Albums ---
    @GetMapping(
            path = "/band/{id}/albums",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Album> getBandAlbums(@PathVariable("id") int bandId) {
        return bandService.getBandAlbums(bandId);
    }

    // --- Advanced ---
    @GetMapping(
            path = "/band/advanced",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> advancedSearch(
            @RequestParam("cityid") Optional<Integer> cityId,
            @RequestParam("countryid") Optional<Integer> countryId,
            @RequestParam("genre") Optional<String> genre
    ) {
        return bandService.advancedSearch(
                cityId.orElse(0),
                countryId.orElse(0),
                genre.orElse("%"));
    }
/*******************************************************************************/

    @PostMapping("/admin/band")
    public ResponseEntity<Band> create(@RequestBody @Valid @NotNull Band band) {
        bandService.save(band);
        return ResponseEntity.ok().body(band);
    }

    @PutMapping("/admin/band")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Band band) {
        Optional<Band> band1 = bandService.getById(band.getId());
        if (Objects.nonNull(band1)) {
            bandService.save(band);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/admin/band/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Band band = bandService.getById(id).orElse(null);
        if(band != null){

            Iterable<ArtistActivity> allArtistActivitiesBands = artistActivityService.getByBandId(band.getId());
            for (ArtistActivity artistActivity : allArtistActivitiesBands) {
                artistActivityService.delete(artistActivity.getId());
            }
            Iterable<BandConcert> allBandConcerts = bandConcertService.getByBandId(band.getId());
            for (BandConcert bandConcert : allBandConcerts) {
                bandConcertService.delete(bandConcert.getId());
            }
            Iterable<Comment> allBandComments = commentService.getCommentsByBandId(band.getId());
            for (Comment comment : allBandComments) {
                commentService.delete(comment.getId());
            }
            Iterable<Rate> allBandRates = rateService.getRatesByBandId(band.getId());
            for (Rate rate : allBandRates) {
                rateService.delete(rate.getId());
            }
            Iterable<Song> allBandSongs = songService.getByBandId(band.getId());
            for (Song song : allBandSongs) {
                song.setBand(null);
                songService.save(song);
            }
            Iterable<UserFavorites> allUserFavoritesLists = userFavoritesService.getAll();
            for (UserFavorites userFavorites : allUserFavoritesLists) {
                Set<Band> userFavoritesBands = userFavorites.getFavoriteBands();
                if(userFavoritesBands.contains(band)) {
                    userFavorites.deleteBandFromFavorites(band);
                }
            }

            bandService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

}
