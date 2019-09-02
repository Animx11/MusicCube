package musiccube.modelgenerator;

import musiccube.entities.Band;
import musiccube.services.band.BandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DataLoader {
    private Logger logger = Logger.getLogger(DataLoader.class);

    @Autowired
    private LineupHandler lineupHandler;
    @Autowired
    private BandHandler bandHandler;
    @Autowired
    private DiscographyHandler discographyHandler;

    @PostMapping(value = "/admin/generate")
    public ResponseEntity<String> generate(
            @RequestParam(required = false, name = "offset") String offset,
            @RequestParam(required = false, name = "iterations") String iterations
    ) throws IOException, InterruptedException {

        logger.info("\n\nGENERATING NEW MODEL\n");

        int offsetNumber = Constants.OFFSET;
        int iterationsCount = Constants.ITERATIONS;

        if (offset != null) {
            try {
                offsetNumber = Integer.parseInt(offset);
            } catch (NumberFormatException e) {
                logger.error("Invalid offset value: "+offset);
                return ResponseEntity.badRequest().body("Expecting integer parameters: offset, iterations");
            }
        }
        if (iterations != null) {
            try {
                iterationsCount = Integer.parseInt(iterations);
            } catch (NumberFormatException e) {
                logger.error("Invalid iterations value: "+iterations);
                return ResponseEntity.badRequest().body("Expecting integer parameters: offset, iterations");
            }
        }

        iterationsCount += offsetNumber;
        logger.info("Fetching bands from "+offsetNumber+" to "+(iterationsCount-1));

        for (;offsetNumber < iterationsCount;offsetNumber++) {
            logger.info("\nProcessing band "+offsetNumber);
            Band band = bandHandler.getBand(offsetNumber);
            if (band != null) {
                lineupHandler.getLineup(band);
                discographyHandler.getAlbums(band);
            }
            else
                logger.warn("Skipping band "+offsetNumber);
        }
        return ResponseEntity.ok("Model generated.");
    }
}
