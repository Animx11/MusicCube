package musiccube.services.person;

import musiccube.entities.Artist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import musiccube.entities.Person;
import musiccube.repositories.PersonRepository;
import org.springframework.data.domain.PageRequest;
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
    public Iterable<Person> getAllPaging(Integer pageNr, Integer perPage) {
        return personRepository.findAll(new PageRequest(pageNr,perPage));
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
    @Override
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public Iterable<Person> getByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @Override
    public Iterable<Person> getByLastNameOrFristNames(String lastName) {
        return personRepository.findByLastNameOrFristNames(lastName);
    }


    @Override
    public Iterable<Person> getByCity(String cityName) {
        return personRepository.findByCity(cityName);
    }

    @Override
    public Iterable<Artist> getByAnything(String input) {
        return personRepository.findByAnything(input);
    }

}
