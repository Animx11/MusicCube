package MusicCube.repositories;

import MusicCube.entities.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {
    Iterable<Song> findByName(String name);
}
