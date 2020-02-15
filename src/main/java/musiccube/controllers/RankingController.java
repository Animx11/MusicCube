package musiccube.controllers;

import musiccube.dtos.RankingDto;
import musiccube.rankings.RankingConstants;
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
    public List<RankingDto> getTopSongs(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RankingConstants.SONG,size) : new ArrayList<>();
    }
    @GetMapping(
            path = "/band",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RankingDto> getTopBands(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RankingConstants.BAND,size) : new ArrayList<>();
    }
    @GetMapping(
            path = "/artist",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RankingDto> getTopArtists(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RankingConstants.ARTIST,size) : new ArrayList<>();
    }
    @GetMapping(
            path = "/album",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RankingDto> getTopAlbums(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RankingConstants.ALBUM,size) : new ArrayList<>();
    }
}
