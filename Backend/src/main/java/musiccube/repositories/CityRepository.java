package musiccube.repositories;

import musiccube.entities.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City,Integer> {

    boolean existsByCityName(String cityName);
    City findByCityName(String cityName);

    @Query("SELECT c FROM City c WHERE LOWER(c.cityName) LIKE LOWER(CONCAT(?1,'%'))")
    Iterable<City> findIterableCityByCityName(String cityName);
}
