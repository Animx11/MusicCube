package musiccube.repositories;

import musiccube.entities.Genre;
import musiccube.entities.Subgenre;
import org.springframework.data.repository.CrudRepository;

public interface SubgenreRepository extends CrudRepository<Subgenre,Integer> {
    Iterable<Subgenre> findByParentGenre(Genre parentGenre);
    Iterable<Subgenre> findByChildGenre(Genre childGenre);
}
