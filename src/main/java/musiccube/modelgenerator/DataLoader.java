package musiccube.modelgenerator;

import musiccube.entities.Band;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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

    @PostMapping(value = "/generate")
    public ResponseEntity<String> generate(@RequestParam Map<String,String> params) throws IOException, InterruptedException {

        logger.info("\n\nGENERATING NEW MODEL\n");

        int offset = Constants.OFFSET;
        int iterations = Constants.ITERATIONS;

        if (params.containsKey("offset")) {
            try {
                offset = Integer.parseInt(params.get("offset"));
            } catch (NumberFormatException e) {
                logger.error("Invalid offset value: "+params.get("offset"));
                return ResponseEntity.badRequest().body("Expecting integer parameters: offset, iterations");
            }
        }
        if (params.containsKey("iterations")) {
            try {
                iterations = Integer.parseInt(params.get("iterations"));
            } catch (NumberFormatException e) {
                logger.error("Invalid iterations value: "+params.get("iterations"));
                return ResponseEntity.badRequest().body("Expecting integer parameters: offset, iterations");
            }
        }

        iterations += offset;
        logger.info("Fetching bands from "+offset+" to "+iterations);

        for (int i=offset;i < iterations;i++) {
            logger.info("\nProcessing band "+i);
            Band band = bandHandler.getBand(i);
            if (band != null) {
                lineupHandler.getLineup(band);
                discographyHandler.getAlbums(band);
            }
            else
                logger.warn("Band (offset: "+i+") json lacking important fields, ignoring.");
        }
        return ResponseEntity.ok("Model generated.");
    }
}
