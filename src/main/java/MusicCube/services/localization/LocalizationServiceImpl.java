package MusicCube.services.localization;

import MusicCube.entities.Localization;
import MusicCube.repositories.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    @Autowired
    private LocalizationRepository localizationRepository;

    @Override
    public Optional<Localization> getById(int id) {
        return localizationRepository.findById(id);
    }
    @Override
    public Iterable<Localization> getAll() {
        return localizationRepository.findAll();
    }
    @Override
    public Localization save(Localization localization) {
        return localizationRepository.save(localization);
    }
    @Override
    public void delete(int id) {
        localizationRepository.deleteById(id);
    }

    @Override
    public Iterable<Localization> getByCountry(String country) {
        return localizationRepository.findByCountry(country);
    }
    @Override
    public Iterable<Localization> getByCity(String city) {
        return localizationRepository.findByCity(city);
    }
    @Override
    public Iterable<Localization> getByAddress(String address) {
        return localizationRepository.findByAddress(address);
    }
    @Override
    public Iterable<Localization> getByAnything(String input) {return localizationRepository.findByAny(input);}
}
