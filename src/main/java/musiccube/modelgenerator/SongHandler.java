package musiccube.modelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;
import musiccube.services.song.SongService;
import musiccube.deserializers.SongDeserializer;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SongHandler {
    private SongHandler() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Song.class, new SongDeserializer());
        mapper.registerModule(module);
    }
    @Autowired
    private SongService songService;
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(SongHandler.class);

    public void getSongs(JSONArray media, Album album, Band band) {
        Constants.arrayToStream(media)
                .map(JSONObject.class::cast)
                .forEach(jsonObject -> Constants.arrayToStream(jsonObject.getJSONArray("tracks"))
                        .map(JSONObject.class::cast)
                        .forEach(track -> {
                            try {
                                Song song = mapper.readValue(track.toString(), Song.class);
                                song.setBand(band);
                                song.setAlbum(album);

                                songService.save(song);
                                logger.info("Song "+song.getSongName()+" by "+band.getBandName()+Constants.SAVED);
                            }
                            catch (Exception e) {
                                logger.error(e.getMessage());
                            }
                        })
                );
    }
}
