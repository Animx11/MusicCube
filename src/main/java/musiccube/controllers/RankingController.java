package musiccube.controllers;

import musiccube.dtos.RatingDto;
import musiccube.dtos.SongRatingDto;
import musiccube.ratings.RatingConstants;
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
    public List<RatingDto> getTopSongs(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RatingConstants.SONG,size) : new ArrayList<>();
    }
    @GetMapping(
            path = "/band",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RatingDto> getTopBands(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RatingConstants.BAND,size) : new ArrayList<>();
    }
    @GetMapping(
            path = "/artist",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RatingDto> getTopArtists(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RatingConstants.ARTIST,size) : new ArrayList<>();
    }
    @GetMapping(
            path = "/album",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RatingDto> getTopAlbums(@RequestParam(name = "size") int size) {
        return size > 0 ? rateService.getBestRated(RatingConstants.ALBUM,size) : new ArrayList<>();
    }
}
