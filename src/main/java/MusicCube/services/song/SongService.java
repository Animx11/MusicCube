package MusicCube.services.song;

import MusicCube.entities.Song;

import java.util.Optional;

public interface SongService {

    Optional<Song> getById(int id);
    Iterable<Song> getAll();
    Song save(Song song);
    void delete(int id);

}