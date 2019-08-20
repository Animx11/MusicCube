package musiccube.modelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import musiccube.deserializers.BandDeserializer;
import musiccube.entities.Band;
import musiccube.services.band.BandService;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class BandHandler {
    private static BandHandler instance = null;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private LocationHandler locationHandler;

    @Autowired
    private BandService bandService;

    private BandHandler() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Band.class,new BandDeserializer());
        mapper.registerModule(module);
    }

    public static BandHandler getInstance() {
        if (instance == null)
            instance = new BandHandler();
        return instance;
    }

    /*
    Gets one band, saves it and returns for further processing
     */
    public Band getBand(int offset) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        Logger logger = Logger.getLogger(BandHandler.class);
        try {
            Thread.sleep(Constants.WAIT);
            ResponseEntity<String> response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/?query=type:group&limit=1&offset="+Integer.toString(offset)+"&fmt=json", String.class);
            JSONObject obj = new JSONObject(response.getBody()).getJSONArray("artists").getJSONObject(0);
            if (checkBand(obj)) {
                Band band = mapper.readValue(obj.toString(), Band.class);
                band.setFormedIn(locationHandler.getCity(
                        obj.getJSONObject("area").getString("id"),
                        obj.getJSONObject("begin-area").getString("id")
                ));
                bandService.save(band);
                logger.info("Band "+obj.getString("name")+Constants.SAVED);
                return  band;
            }
        } catch (JSONException je) {
            logger.warn("Band is causing problems, ignoring");
        }
        return null;
    }

    /*
    Checks, if fetched band data contains most important fields
     */
    private boolean checkBand(JSONObject obj) {
        return obj.has("area") &&
                obj.has("begin-area") &&
                obj.has("name") &&
                obj.has(Constants.LF_SPAN);

    }
}
