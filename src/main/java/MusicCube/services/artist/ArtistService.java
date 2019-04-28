package MusicCube.services.artist;

import MusicCube.entities.Artist;

import java.util.Optional;

public interface ArtistService {

    Optional<Artist> getById(int id);
    Iterable<Artist> getAll();
    Artist save(Artist artist);
    void delete(int id);
    Iterable<Artist> getByStageName(String stageName);

}