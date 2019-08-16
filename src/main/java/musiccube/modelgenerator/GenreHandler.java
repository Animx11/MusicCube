package musiccube.modelgenerator;

import musiccube.entities.Genre;
import musiccube.services.genre.GenreService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GenreHandler {
    private GenreHandler() {}
    private Logger logger = Logger.getLogger(GenreHandler.class);
    @Autowired
    private GenreService genreService;

    public Genre addGenre(JSONObject track) {

        Genre genre = null;

        Optional<JSONObject> genreJSON = Constants.arrayToStream(track.getJSONObject("recording").getJSONArray("genres"))
                                    .map(JSONObject.class::cast)
                                    .reduce((genreA,genreB)
                                            -> genreA.getInt("count") > genreB.getInt("count") ? genreA : genreB
                                    );

        if (genreJSON.isPresent()) {
            String name = genreJSON.get().getString("name");
            if (genreService.existsByName(name)) {
                genre = genreService.getOneByGenreName(name);
                logger.info("Genre "+genre.getGenreName()+Constants.EXISTS);
            }
            else {
                genre = new Genre(null,name,null);
                genreService.save(genre);
                logger.info("Genre "+genre.getGenreName()+Constants.SAVED);
            }
        }
        else {
            logger.warn("Couldn't get genre of track: "+track.getString("title")+", mbid: "+track.getString("id"));
        }

        return genre;
    }
}
