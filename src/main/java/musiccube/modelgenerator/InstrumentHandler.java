package musiccube.modelgenerator;

import musiccube.entities.Artist;
import musiccube.entities.ArtistInstrument;
import musiccube.entities.Instrument;
import musiccube.entities.InstrumentType;
import musiccube.services.artist.ArtistService;
import musiccube.services.artistinstrument.ArtistInstrumentService;
import musiccube.services.instrument.InstrumentService;
import musiccube.services.instrumentType.InstrumentTypeService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Component
public class InstrumentHandler {
    private Logger logger = Logger.getLogger(InstrumentHandler.class);
    @Autowired
    private InstrumentService instrumentService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private InstrumentTypeService instrumentTypeService;
    @Autowired
    private ArtistInstrumentService artistInstrumentService;
    @Autowired
    private InstrumentTypeHandler instrumentTypeHandler;

    public void handleInstrument(String name, Artist artist) {
        Instrument instrument;

        if (instrumentService.existsByInstrumentName(name)) { // instrument is in database
            instrument = instrumentService.getOneByName(name);
            logger.info("Instrument " + instrument.getInstrumentName() + Constants.EXISTS);
            // if not registered to artist
            if ( ! artistInstrumentService.existsByArtistIdAndInstrumentId(
                    artist.getId(),
                    instrument.getId()
            )) {
                saveArtistInstrument(artist,instrument);
            } else { // if registered
                logger.info(
                        "Relationship between " +
                        artist.getStageName() +
                        " and " +
                        instrument.getInstrumentName() +
                        Constants.EXISTS
                );
            }
        } else try { // instrument doesn't exist
            JSONObject instrJson = findCorrectJson(getInstrumentArray(name), name);
            if (instrJson != null) { // save new and register to artist
                InstrumentType type = null;
                if (instrJson.has("type")) {
                    type = instrumentTypeHandler.handleType(instrJson.getString("type"));
                }
                instrument = new Instrument(name,type);
                instrumentService.save(instrument);
                saveArtistInstrument(artist,instrument);
            } else {
                logger.warn("Couldn't find instrument "+name);
            }
        } catch (InterruptedException ie) {
            logger.error("Interrupted!");
        }
    }

    private JSONArray getInstrumentArray(String name) throws InterruptedException {
        Thread.sleep(Constants.WAIT);
        ResponseEntity<String> response = (new RestTemplate()).getForEntity(
                "https://musicbrainz.org/ws/2/instrument?fmt=json&query=\""+name+"\"",
                String.class
        );
        System.out.println(new JSONObject(response.getBody()));
        return new JSONObject(response.getBody()).getJSONArray("instruments");
    }

    private JSONObject findCorrectJson(JSONArray instrArr, String name) {
        System.out.println(name+'\n'+instrArr.toString());
        Optional<JSONObject> opt = Constants.arrayToStream(instrArr)
                                        .map(JSONObject.class::cast)
                                        .filter(jsonObject -> jsonObject.getString("name").equals(name))
                                        .findFirst();
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    private void saveArtistInstrument(Artist artist, Instrument instrument) {
        artistInstrumentService.save(new ArtistInstrument(artist,instrument));
        logger.info(artist.getStageName() + " now plays on " + instrument.getInstrumentName());
    }
}
