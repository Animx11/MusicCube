package MusicCube.repositories;

import MusicCube.entities.Genre;
import MusicCube.entities.Subgenre;
import org.springframework.data.repository.CrudRepository;

public interface SubgenreRepository extends CrudRepository<Subgenre,Integer> {
    Iterable<Subgenre> findByParentGenre(Genre parentGenre);
    Iterable<Subgenre> findByChildGenre(Genre childGenre);
}
