package MusicCube.repositories;

import MusicCube.entities.SongAuthorship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface SongAuthorshipRepository extends CrudRepository<SongAuthorship,Integer> {
    @Query("select s from SongAuthorship s where s.song.id = ?1")
    Iterable<SongAuthorship> findBySongId(int id);
    @Query("select s from SongAuthorship s where s.author.id = ?1")
    Iterable<SongAuthorship> findByAuthorId(int id);
}
