package MusicCube.services.localization;

import MusicCube.entities.Localization;

import java.util.Optional;

public interface LocalizationService {

    Optional<Localization> getById(int id);
    Iterable<Localization> getAll();
    Localization save(Localization localization);
    void delete(int id);

    Iterable<Localization> getByCountry(String country);
    Iterable<Localization> getByCity(String city);
    Iterable<Localization> getByAddress(String address);
    Iterable<Localization> getByAnything(String input);

}
