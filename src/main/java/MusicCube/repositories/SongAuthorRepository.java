package MusicCube.repositories;

import MusicCube.entities.SongAuthor;
import org.springframework.data.repository.CrudRepository;

public interface SongAuthorRepository extends CrudRepository<SongAuthor,Integer> {
}
