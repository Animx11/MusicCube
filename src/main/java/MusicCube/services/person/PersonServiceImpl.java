package MusicCube.services.person;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import MusicCube.entities.Person;
import MusicCube.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<Person> getById(int id) {
        return personRepository.findById(id);
    }
    @Override
    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }
    @Override
    public Iterable<Person> getByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }
    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
    @Override
    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
