package musiccube.controllers;

import musiccube.entities.Song;
import musiccube.services.song.SongService;
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
public class SongController {

    @Autowired
    private SongService songService;

    // --- Get by id ---
    @GetMapping(
            path = "/song/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Song> getById(@PathVariable("id") int id) {
        Optional<Song> song = songService.getById(id);
        return song.isPresent() ?
                ResponseEntity.ok(song.get()) :
                ResponseEntity.notFound().build();
    }

    // --- Get all songs ---
    @GetMapping(
            path = "/song",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getAll() {
        return songService.getAll();
    }

    // --- Get all songs with paging ---
    @GetMapping(
            path = "song/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage) {
        return perPage == null ?
                songService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                songService.getAllPaging(pageNr, perPage);
    }

    // --- Get by song name ---
    @GetMapping(
            path = "/song/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getBySongName(@PathVariable("name") String songName) {
        return songService.getBySongName(songName);
    }

/**********************************************************************************/

    @PostMapping("/admin/song")
    public ResponseEntity<Song> create(@RequestBody @Valid @NotNull Song song) {
        songService.save(song);
        return ResponseEntity.ok().body(song);
    }

    @PutMapping("/admin/song")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Song song) {
        Optional<Song> song1 = songService.getById(song.getId());
        if (Objects.nonNull(song1)) {
            songService.save(song);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/song/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
