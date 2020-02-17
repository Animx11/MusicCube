package musiccube.controllers;

import musiccube.entities.Song;
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
    @GetMapping(path = "/song",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Song> song(@RequestParam Map<String,String> params) {
        return songService.advanced(params);
    }
}
