package musiccube.modelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import musiccube.deserializers.ArtistDeserializer;
import musiccube.deserializers.ArtistInBandDeserializer;
import musiccube.entities.Artist;
import musiccube.entities.Activity;
import musiccube.entities.Band;
import musiccube.services.artist.ArtistService;
import musiccube.services.artistactivity.ArtistActivityService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class LineupHandler {

    private static LineupHandler instance;

    private Logger logger = Logger.getLogger(LineupHandler.class);
    private ResponseEntity<String> response;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    
    private static final String ARTIST = "Artist ";

    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistActivityService artistActivityService;
    @Autowired
    private InstrumentHandler instrumentHandler;
    @Autowired
    private LocationHandler locationHandler;

    private LineupHandler() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Artist.class, new ArtistDeserializer());
        module.addDeserializer(Activity.class, new ArtistInBandDeserializer());
        mapper.registerModule(module);
    }
    public static LineupHandler getInstance() {
        if (instance == null)
            instance = new LineupHandler();
        return instance;
    }

    /*
    Gets members of band, calls getArtist for each of them
     */
    public void getLineup(Band band) throws IOException, InterruptedException {
        Thread.sleep(Constants.WAIT);
        response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/"+band.getMbId()+"?inc=artist-rels&fmt=json",String.class);
        JSONObject obj = new JSONObject(response.getBody());
        JSONArray relations = obj.getJSONArray("relations");
        for (int i = 0; i < relations.length(); i++) {
            obj = relations.getJSONObject(i);
            if (obj.getString("type").equals("member of band")) {
                Artist artist;
                if ( (artist = getArtist(obj.getJSONObject("artist").getString("id"))) != null) {
                    signToBand(artist, band, obj);
                }
            }
        }
    }

    /*
    Creates, saves and returns new artist, or finds and returns existing one
     */
    private Artist getArtist(String mbid) throws IOException, InterruptedException {
        Artist artist;
        if (artistService.existsByMbId(mbid)) {
            return artistService.getByMbId(mbid);
        }
        else {
            try {
                Thread.sleep(Constants.WAIT);
                response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/" + mbid + Constants.FMT, String.class);
                JSONObject obj = new JSONObject(response.getBody());
                if (checkArtist(obj)) {
                    artist = mapper.readValue(obj.toString(), Artist.class);
                    artist.setOrigin(locationHandler.getCity(
                            obj.getJSONObject("area").getString("id"),
                            obj.getJSONObject(Constants.BGN_AREA).getString("id")
                    ));
                    artistService.save(artist);
                    logger.info(ARTIST + artist.getStageName() + Constants.SAVED);
                    return artist;
                } else {
                    logger.warn(ARTIST+mbid+" json incomplete, ignoring.");
                }

            } catch (JSONException je) {
                logger.warn(ARTIST+mbid+" causing problems, ignoring");
            }
        }
        return null;
    }

    /*
    Checks if artist json includes required info
     */
    private boolean checkArtist(JSONObject obj) {
        return obj.has("name") &&
                obj.has(Constants.LF_SPAN) &&
                obj.has("area") &&
                obj.has(Constants.BGN_AREA) &&
                ! obj.isNull(Constants.BGN_AREA) &&
                ! obj.getJSONObject(Constants.LF_SPAN).isNull("begin");
    }

    /*
    Creates ArtistActivity object for given relation
     */
    private void signToBand(Artist artist, Band band, JSONObject relation) throws IOException {
        Activity activity = mapper.readValue(relation.toString(), Activity.class);
        activity.setArtist(artist);
        activity.setBand(band);
        artistActivityService.save(activity);
        logger.info((new StringBuilder())
                .append(ARTIST)
                .append(artist.getStageName())
                .append(" added to band ")
                .append(band.getBandName())
                .append(".")
                .toString()
        );

        if (relation.has("attributes") && relation.getJSONArray("attributes").length() > 0) {
            Constants.arrayToStream(relation.getJSONArray("attributes"))
                    .map(String.class::cast)
                    .filter(s -> ! (
                                    s.equals("original") ||
                                    s.equals("lead vocals") ||
                                    s.equals("background vocals")
                            ))
                    .forEach(s -> {
                        instrumentHandler.handleInstrument(s,artist);
                    });
        }
    }
}
