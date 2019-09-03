package musiccube.controllers;

import musiccube.entities.Person;
import musiccube.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            path = "/person/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Person> getById(@PathVariable("id") int id) {
        Optional<Person> person = personService.getById(id);
        return person.isPresent() ?
                ResponseEntity.ok(person.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/person",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Person> getAll() {
        return personService.getAll();
    }

    // --- Get all persons with paging ---
    @GetMapping(
            path = "person/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Person> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage) {
        return perPage == null ?
                personService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                personService.getAllPaging(pageNr, perPage);
    }

    // --- GET BY LAST NAME ---
    @GetMapping(
            path = "/person/lastname/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Person> getByLastName(@PathVariable("name") String lastName) {
        return personService.getByLastName(lastName);
    }

    @PostMapping("/admin/person")
    public ResponseEntity<Person> create(@RequestBody @Valid @NotNull Person person) {
        personService.save(person);
        return ResponseEntity.ok().body(person);
    }

    @PutMapping("/admin/person")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Person person) {
        Optional<Person> person1 = personService.getById(person.getId());
        if (Objects.nonNull(person1)) {
            personService.save(person);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/person/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
