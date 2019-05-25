package MusicCube.repositories;

import MusicCube.entities.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    @Query("SELECT c from Country c WHERE c.countryName LIKE CONCAT('%',?1,'%')")
    Iterable<Country> findByCountryName(String countryName);

    @Query("SELECT c from Country c WHERE c.code LIKE ?1")
    Iterable<Country> findByCode(String code);

}
