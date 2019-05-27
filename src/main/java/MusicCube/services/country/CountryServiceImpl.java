package MusicCube.services.country;

import MusicCube.entities.Country;
import MusicCube.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Optional<Country> getById(int id) {
        return countryRepository.findById(id);
    }
    @Override
    public Iterable<Country> getAll() {
        return countryRepository.findAll();
    }
    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }
    @Override
    public void delete(int id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Iterable<Country> getByCountryName(String countryName) { return countryRepository.findByCountryName(countryName); }
    @Override
    public Iterable<Country> getByCode(String code) { return countryRepository.findByCode(code); }

    @Override
    public boolean existsByCountryName(String countryName){
        return countryRepository.existsByCountryName(countryName);
    }

}
