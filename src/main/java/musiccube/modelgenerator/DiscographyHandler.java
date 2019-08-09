package musiccube.modelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import musiccube.deserializers.AlbumDeserializer;
import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.services.album.AlbumService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DiscographyHandler {
    private static DiscographyHandler instance = null;
    private RestTemplate restTemplate = new RestTemplate();
    private Logger logger = Logger.getLogger(DiscographyHandler.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private AlbumService albumService;
    @Autowired
    private SongHandler songHandler;

    private static String albumType;

    private DiscographyHandler() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Album.class,new AlbumDeserializer());
        mapper.registerModule(simpleModule);
    }
    public static DiscographyHandler getInstance() {
        if (instance == null)
            instance = new DiscographyHandler();
        return instance;
    }

    /*
    This will get albums
     */
    public void getAlbums(Band band) throws InterruptedException {
        String mbid = band.getMbId();
        filterReleaseGroups(getReleaseGroups(mbid)).forEach(id->{
            try {
                Optional<JSONObject> firstRelease = findFirstRelease(getReleases(id));
                if (firstRelease.isPresent()) {
                    JSONObject albumJSON = getReleaseDetails(firstRelease.get().getString("id"));
                    Album album = mapper.readValue(albumJSON.toString(),Album.class);
                    album.setType(albumType);
                    albumService.save(album);
                    logger.info("Album "+album.getAlbumName()+Constants.SAVED);
                    songHandler.getSongs(albumJSON.getJSONArray("media"), album, band);
                }
                else {
                    logger.warn("Couldn't find first release of release group "+id);
                }
            }
            catch (InterruptedException e) {
                logger.error("Interrupted!",e.fillInStackTrace());
            }
            catch (IOException io) {
                logger.error(io.getMessage());
            }
        });
    }

    /*
    Gets first 100 release-groups of given artist.
     */
    private JSONObject getReleaseGroups(String mbid) throws InterruptedException {
        Thread.sleep(600);
        ResponseEntity<String> response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/release-group/?artist="+mbid+"&fmt=json&limit=100",String.class);
        return new JSONObject(response.getBody());
    }
    /*
    Gets all releases of given album
     */
    private JSONObject getReleases(String groupId) throws InterruptedException {
        Thread.sleep(600);
        ResponseEntity<String> response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/release-group/"+groupId+"?fmt=json&inc=releases",String.class);
        return new JSONObject(response.getBody());
    }
    /*
        Gets details and track list of given release
     */
    private JSONObject getReleaseDetails(String relId) throws InterruptedException {
        Thread.sleep(600);
        ResponseEntity<String> response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/release/"+relId+"?fmt=json&inc=recordings labels genres",String.class);
        return new JSONObject(response.getBody());
    }
    /*
        Returns mbIds of albums that are not compilations, remixes, lives etc.
     */
    private List<String> filterReleaseGroups(JSONObject relGrps) {
        return Constants.arrayToStream(relGrps.getJSONArray("release-groups"))
                .map(JSONObject.class::cast)
                .filter(jsonObject -> jsonObject.getJSONArray("secondary-types").length() == 0)
                .map(jsonObject -> jsonObject.getString("id"))
                .collect(Collectors.toList());
    }
    /*
        Returns first release of album.
     */
    private Optional<JSONObject> findFirstRelease(JSONObject release) {
        if (release.has("primary-type")) {
            albumType = release.getString("primary-type");
        }
        return Constants.arrayToStream(release.getJSONArray("releases"))
                .map(JSONObject.class::cast)
                .filter(jsonObject -> jsonObject.has("date") && !jsonObject.getString("date").isEmpty())
                .filter(jsonObject -> jsonObject
                        .getString("date")
                        .equals(release.getString("first-release-date")))
                .findFirst();
    }
}
