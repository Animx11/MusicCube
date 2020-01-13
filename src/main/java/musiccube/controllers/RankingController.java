package musiccube.controllers;

import musiccube.dtos.SongRatingDto;
import musiccube.services.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@CrossOrigin(origins = "${serverAddress}")
public class RankingController {

    @Autowired
    private RateService rateService;

    @GetMapping(
            path = "/song",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<SongRatingDto> getTopSongs(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRatedSongs(size) : new ArrayList<>();
    }
}
