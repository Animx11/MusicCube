package MusicCube.repositories;

import MusicCube.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {
    Iterable<Person> findByLastName(String lastName);
}
