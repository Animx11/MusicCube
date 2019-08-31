package musiccube.services.artist;

import musiccube.entities.Artist;

import java.util.Optional;

public interface ArtistService {

    Optional<Artist> getById(int id);
    Iterable<Artist> getAll();
    Iterable<Artist> getAllPaging(Integer pageNr, Integer perPage);
    Artist save(Artist artist);
    void delete(int id);

    Iterable<Artist> getByStageName(String stageName);
    Iterable<Artist> getByAnything(String input);
    Artist getByMbId(String mbId);

    boolean existsByMbId(String mbId);

    Iterable<Artist> advancedSearch(int bandId, int cityId);

}