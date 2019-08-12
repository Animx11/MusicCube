package musiccube.services.city;

import musiccube.entities.City;

import java.util.Optional;

public interface CityService {
    Optional<City> getById(int id);
    Iterable<City> getAll();
    City save(City city);
    void delete(int id);

    boolean exists(String cityName);
    City getByCityName(String cityName);
}
