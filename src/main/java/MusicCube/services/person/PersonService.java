package MusicCube.services.person;

import java.util.Optional;
import MusicCube.entities.Person;

public interface PersonService {
    
    Optional<Person> getById(int id);
    Iterable<Person> getAll();
    Iterable<Person> getByLastName(String lastName);
    Person save(Person person);
    void delete(int id);
}
