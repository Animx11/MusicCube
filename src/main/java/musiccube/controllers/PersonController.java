package musiccube.controllers;

import musiccube.entities.Person;
import musiccube.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Person> getById(int id) {
        return personService.getById(id);
    }

    @RequestMapping(value = "/persons",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> getAll() {
        return personService.getAll();
    }
    
    // --- Get all persons with paging ---
    @RequestMapping(value = "persons/{page}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> getAllPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> perPage) {
        return personService.getAllPaging(pageNr,perPage.orElse(10));
    }

    // --- GET BY LAST NAME ---
    @RequestMapping(value = "/persons{lastname}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> getByLastName(String lastName) {
        return personService.getByLastName(lastName);
    }

    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public ResponseEntity<Person> create(@RequestBody @Valid @NotNull Person person) {
        personService.save(person);
        return ResponseEntity.ok().body(person);
    }

    @RequestMapping(value = "/person",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Person person) {
        Optional<Person> person1 = personService.getById(person.getId());
        if (Objects.nonNull(person1)) {
            personService.save(person);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/persons",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> redirect(Model model) {
        return personService.getAll();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        personService.delete(id);
        return new RedirectView("/api/persons",true);
    }



}
