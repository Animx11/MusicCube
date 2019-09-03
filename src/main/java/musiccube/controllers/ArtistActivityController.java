package musiccube.controllers;

import musiccube.entities.Activity;
import musiccube.services.artistactivity.ArtistActivityService;
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
public class ArtistActivityController {

    @Autowired
    private ArtistActivityService artistActivityService;

/*************************** GET ***********************************/
    @GetMapping(
            path = "/artistactivity/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Activity> getById(@PathVariable("id") int id) {
        Optional<Activity> activity = artistActivityService.getById(id);
        return activity.isPresent() ?
                ResponseEntity.ok(activity.get()) : 
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/artistactivity",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Activity> getAll() {
        return artistActivityService.getAll();
    }

    // --- Get by Artist ---
    @GetMapping(
            path = "/artistactivity/artist/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Activity> getByArtist(
            @PathVariable("id") int artistId,
            @RequestParam(name = "active", required = false) Boolean active // zwraca 400 bad request jeśli nie {true, false}
    ) {
        return active == null ?
                artistActivityService.getByArtistId(artistId) :
                artistActivityService.getByArtistIdIsActive(artistId, active);
    }

    // --- Get by Band ---
    @GetMapping(
            path = "/artistactivity/band/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Activity> getByBand(
            @PathVariable("id") int bandId,
            @RequestParam(name = "active", required = false) Boolean active
    ) {
        return active == null ?
                artistActivityService.getByBandId(bandId) :
                artistActivityService.getByBandIdIsActive(bandId, active);
    }
/************************************************************************************/

    @PostMapping(path = "/admin/artistactivity")
    public ResponseEntity<Activity> create(@RequestBody @Valid @NotNull Activity activity) {
        artistActivityService.save(activity);
        return ResponseEntity.ok().body(activity);
    }

    @PutMapping(path = "/admin/artistactivity")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Activity activity) {
        Optional<Activity> artistActivity1 = artistActivityService.getById(activity.getId());
        if (Objects.nonNull(artistActivity1)) {
            artistActivityService.save(activity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/artistactivity/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        artistActivityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
