package MusicCube.repositories;

import MusicCube.entities.Localization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LocalizationRepository extends CrudRepository<Localization,Integer> {

    @Query("SELECT l FROM Localization l WHERE l.country LIKE CONCAT('%',?1,'%') OR l.city LIKE CONCAT('%',?1,'%') OR l.address LIKE CONCAT('%',?1,'%')")
    Iterable<Localization> findByAny(String input);

    Iterable<Localization> findByCountry(String country);
    Iterable<Localization> findByCity(String city);
    Iterable<Localization> findByAddress(String address);

}
