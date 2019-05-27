package MusicCube.repositories;

import MusicCube.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {

    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Person> findByLastName(String lastName);
}
