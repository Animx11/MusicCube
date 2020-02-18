package musiccube.controllers;

import musiccube.entities.Artist;
import musiccube.entities.Band;
import musiccube.entities.Song;
import musiccube.services.artist.ArtistService;
import musiccube.services.band.BandService;
import musiccube.services.song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "${serverAddress}")
public class AdvancedSearchController {
    @Autowired
    private SongService songService;
    @Autowired
    private BandService bandService;
    @Autowired
    private ArtistService artistService;

    @GetMapping(path = "/song", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> song(@RequestParam Map<String, String> params) {
        return songService.advanced(params);
    }

    @GetMapping(path = "/band", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Band> band(@RequestParam Map<String, String> params) {
        return bandService.advanced(params);
    }

    @GetMapping(path = "/artist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Artist> artist(@RequestParam Map<String, String> params) {
        return artistService.advanced(params);
    }
}
    
