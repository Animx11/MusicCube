package MusicCube.repositories;

import MusicCube.entities.Concert;
import org.springframework.data.repository.CrudRepository;

public interface ConcertRepository extends CrudRepository<Concert,Integer> {
    Iterable<Concert> findByName(String name);
}