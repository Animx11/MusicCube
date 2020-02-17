package musiccube.controllers;

import musiccube.entities.Band;
import musiccube.services.band.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/recommend")
@CrossOrigin(origins = "${serverAddress}")
public class RecommendationsController {
    @Autowired
    private BandService bandService;

    @GetMapping(path = "/band",produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Band> bands(
            @RequestParam("band") int bandId,
            @RequestParam("limit") int limit,
            @RequestParam(required = false,value = "user") Optional<String> userName
    ) {
        return bandService.getSimilar(bandId,limit,userName);
    }
}
