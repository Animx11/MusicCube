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

    @PostConstruct
    public void generate() throws IOException, InterruptedException {
        for (int i=0;i<Constants.ITERATIONS;i++) {
            Band band = bandHandler.getBand(i);
            if (band != null)
                lineupHandler.getLineup(band);
            else
                logger.warn("Band json lacking important fields, ignoring.");
        }
    }
}
