package musiccube.services.songauthorship;

import musiccube.entities.SongAuthorship;

import java.util.Optional;

public interface SongAuthorshipService {

    Optional<SongAuthorship> getById(int id);
    Iterable<SongAuthorship> getAll();
    SongAuthorship save(SongAuthorship songAuthorship);
    void delete(int id);

    Iterable<SongAuthorship> getBySongId(int id);
    Iterable<SongAuthorship> getByAuthorId(int id);

}