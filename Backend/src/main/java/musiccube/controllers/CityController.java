package musiccube.controllers;

import musiccube.entities.Band;
import musiccube.entities.City;
import musiccube.entities.Concert;
import musiccube.entities.Person;
import musiccube.services.band.BandService;
import musiccube.services.city.CityService;
import musiccube.services.concert.ConcertService;
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
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private BandService bandService;

    @Autowired
    private PersonService personService;

    @GetMapping(
            path = "/city/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<City> getById(@PathVariable("id") int id) {
        Optional<City> city = cityService.getById(id);
        return city.isPresent() ?
                ResponseEntity.ok(city.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/city",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<City> getAll() {
        return cityService.getAll();
    }


    @GetMapping(
            path = "/city/name-auto/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> getByCityName(@PathVariable("name") String cityName){
        return cityService.getIterableCityByCityName(cityName);
    }

    @PostMapping("/admin/city")
    public ResponseEntity<City> create(@RequestBody @Valid @NotNull City city) {
        cityService.save(city);
        return ResponseEntity.ok().body(city);
    }

    @PutMapping("/admin/city")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull City city) {
        Optional<City> genre1 = cityService.getById(city.getId());
        if (Objects.nonNull(genre1)) {
            cityService.save(city);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/city/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        City city = cityService.getById(id).orElse(null);
        Iterable<Concert> allConcertByCity = concertService.getByCityName(city.getCityName());
        Iterable<Person> allPersonByCity = personService.getByCity(city.getCityName());
        Iterable<Band> allBandByCity = bandService.getByCity(city.getCityName());

        for (Concert concert : allConcertByCity) {
            concert.setConcertCity(null);
            concertService.save(concert);
        }
        for (Person person : allPersonByCity) {
            person.setOrigin(null);
            personService.save(person);
        }
        for (Band band : allBandByCity) {
            band.setFormedIn(null);
            bandService.save(band);
        }

        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
