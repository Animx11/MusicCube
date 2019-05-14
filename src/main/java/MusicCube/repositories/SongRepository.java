package MusicCube.repositories;

import MusicCube.entities.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {

    @Query("SELECT s FROM Song s where s.songName LIKE CONCAT('%',?1,'%')")
    Iterable<Song> findBySongName(String songName);
}
