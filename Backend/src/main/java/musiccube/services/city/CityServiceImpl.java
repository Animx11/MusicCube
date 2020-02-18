package musiccube.services.city;

import musiccube.entities.City;
import musiccube.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

    @Override
    public boolean exists(String cityName) {
        return cityRepository.existsByCityName(cityName);
    }

    @Override
    public City getByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    @Override
    public Iterable<City> getIterableCityByCityName(String cityName) {
        return cityRepository.findIterableCityByCityName(cityName);
    }

    @Override
    public Iterable<City> getIterableCityByCountry(String countryName) {
        return cityRepository.findIterableCityByCountry(countryName);
    }
}
