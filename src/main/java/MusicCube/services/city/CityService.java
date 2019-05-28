package MusicCube.services.city;

import MusicCube.entities.City;

import java.util.Optional;

public interface CityService {
    Optional<City> getById(int id);
    Iterable<City> getAll();
    City save(City city);
    void delete(int id);
}