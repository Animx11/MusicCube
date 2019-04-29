package MusicCube.repositories;

import MusicCube.entities.Person;
import MusicCube.entities.Song;
import MusicCube.entities.SongAuthorship;
import MusicCube.entities.SongAuthorship;
import org.springframework.data.repository.CrudRepository;

public interface SongAuthorshipRepository extends CrudRepository<SongAuthorship,Integer> {
    Iterable<SongAuthorship> findBySong(Song song);
    Iterable<SongAuthorship> findByAuthor(Person person);
}
