package musiccube.services.artist;

import musiccube.entities.Artist;

import java.util.Map;
import java.util.Optional;

public interface ArtistService {

    Optional<Artist> getById(int id);
    Iterable<Artist> getAll();
    Iterable<Artist> getAllPaging(Integer pageNr, Integer perPage);
    Artist save(Artist artist);
    void delete(int id);

    Iterable<Artist> getByStageName(String stageName);
    Iterable<Artist> getByCity(String cityName);
    Iterable<Artist> getByAnything(String input);
    Artist getByMbId(String mbId);

    boolean existsByMbId(String mbId);

    Iterable<Artist> advancedSearch(
            Optional<Integer> band,
            Optional<Integer> country,
            Optional<Integer> city,
            Optional<Integer> instrument
    );

    Iterable<Artist> advanced(Map<String, String> params);
}