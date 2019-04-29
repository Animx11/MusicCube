package MusicCube.repositories;

import MusicCube.entities.Band;
import org.springframework.data.repository.CrudRepository;


public interface BandRepository extends CrudRepository<Band,Integer> {
    Iterable<Band> findByName(String name);
}
