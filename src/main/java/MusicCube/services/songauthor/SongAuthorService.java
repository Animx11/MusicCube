package MusicCube.services.songauthor;

import MusicCube.entities.SongAuthor;

import java.util.Optional;

public interface SongAuthorService {

    Optional<SongAuthor> getById(int id);
    Iterable<SongAuthor> getAll();
    SongAuthor save(SongAuthor songAuthor);
    void delete(int id);

}