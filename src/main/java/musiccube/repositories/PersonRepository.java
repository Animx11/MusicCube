package musiccube.repositories;

import musiccube.entities.Artist;
import musiccube.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends CrudRepository<Person,Integer>, PagingAndSortingRepository<Person,Integer> {

    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Person> findByLastName(String lastName);

    @Query("SELECT p FROM Person p WHERE LOWER(p.stageName) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(p.firstNames) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(p.lastName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Artist> findByAnything(String input);

    @Query("SELECT p FROM Person p WHERE p.origin.cityName LIKE ?1")
    Iterable<Person> findByCity(String cityName);

}
