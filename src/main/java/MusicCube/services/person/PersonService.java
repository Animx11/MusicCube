package MusicCube.services.person;

import java.util.Optional;
import MusicCube.entities.Person;

public interface PersonService {
    
    Optional<Person> getById(int id);
    Iterable<Person> getAll();
    Person save(Person person);
    void delete(int id);

    Iterable<Person> getByLastName(String lastName);
}
