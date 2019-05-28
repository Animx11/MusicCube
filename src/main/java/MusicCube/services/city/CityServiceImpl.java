package MusicCube.services.city;

import MusicCube.entities.City;
import MusicCube.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> getById(int id) {
        return cityRepository.findById(id);
    }
    @Override
    public Iterable<City> getAll() {
        return cityRepository.findAll();
    }
    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }
    @Override
    public void delete(int id) {
        cityRepository.deleteById(id);
    }
}
