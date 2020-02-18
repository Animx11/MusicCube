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

import java.io.IOException;

@Component
public class SongHandler {
    private SongHandler() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Song.class, new SongDeserializer());
        mapper.registerModule(module);
    }
    @Autowired
    private SongService songService;
    @Autowired
    private GenreHandler genreHandler;
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(SongHandler.class);

    /*
        Iterates through media object, saves tracks as Songs
     */
    public void getSongs(JSONArray media, Album album, Band band) throws IOException {
        JSONArray tracks;
        int trackNumber = 1;
        for (int i=0; i<media.length(); i++) {
            tracks = media.getJSONObject(i).getJSONArray("tracks");
            for (int j=0; j<tracks.length(); j++) {
                JSONObject track = tracks.getJSONObject(j);
                Song song = mapper.readValue(track.toString(),Song.class);
                song.setBand(band);
                song.setAlbum(album);
                song.setGenre(genreHandler.addGenre(track));
                song.setTrackNumber(trackNumber++);
                songService.save(song);
                logger.info("Song "+song.getSongName()+" by "+band.getBandName()+Constants.SAVED);
            }
        }
    }
}
