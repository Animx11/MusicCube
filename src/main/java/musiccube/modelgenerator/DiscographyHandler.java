package musiccube.modelgenerator;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DiscographyHandler {
    private static DiscographyHandler instance = null;

    private DiscographyHandler() {}
    public static DiscographyHandler getInstance() {
        if (instance == null)
            instance = new DiscographyHandler();
        return instance;
    }

    /*
    TODO: This will get albums
     */
    private void getAlbums(String mbid) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        Thread.sleep(600);
        ResponseEntity<String> response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/release/?artist="+mbid+"&inc=artist-rels&fmt=json",String.class);
        JSONObject obj = new JSONObject(response.getBody());
    }
}
