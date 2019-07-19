package MusicCube.repositories;

import MusicCube.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City,Integer> {

    boolean existsByCityName(String cityName);
    City findByCityName(String cityName);
}
