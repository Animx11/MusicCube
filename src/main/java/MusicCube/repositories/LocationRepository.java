package MusicCube.repositories;

import MusicCube.entities.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,Integer> {

    Iterable<Location> findByCity(String city);

}
