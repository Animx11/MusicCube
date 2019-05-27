package MusicCube.repositories;

import MusicCube.entities.Album;
import MusicCube.entities.Band;
import MusicCube.entities.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Integer> {

    @Query("SELECT s FROM Song s where s.songName LIKE CONCAT('%',?1,'%')")
    Iterable<Song> findBySongName(String songName);

    boolean existsBySongName(String songName);
    boolean existsByAlbum(Album album);
    boolean existsByBand(Band band);
}
