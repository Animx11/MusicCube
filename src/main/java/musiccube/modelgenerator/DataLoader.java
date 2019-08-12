package musiccube.modelgenerator;

import musiccube.entities.Band;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class DataLoader {
    private Logger logger = Logger.getLogger(DataLoader.class);

    @Autowired
    private LineupHandler lineupHandler;
    @Autowired
    private BandHandler bandHandler;
    @Autowired
    private DiscographyHandler discographyHandler;

    @PostConstruct
    public void generate() throws IOException, InterruptedException {
        int iterations = Constants.ITERATIONS + Constants.OFFSET;
        for (int i=Constants.OFFSET;i < iterations;i++) {
            Band band = bandHandler.getBand(i);
            if (band != null) {
                lineupHandler.getLineup(band);
                discographyHandler.getAlbums(band);
            }
            else
                logger.warn("Band (offset: "+i+") json lacking important fields, ignoring.");
        }
    }
}
