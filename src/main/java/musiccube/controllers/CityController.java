package musiccube.controllers;

import musiccube.entities.City;
import musiccube.services.city.CityService;
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
@CrossOrigin(origins = "${serverAddress}")
public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/city{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<City> getById(int id) {
        return cityService.getById(id);
    }

    @RequestMapping(value = "/cities",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> getAll() {
        return cityService.getAll();
    }

    @RequestMapping(value = "/city",method = RequestMethod.POST)
    public ResponseEntity<City> create(@RequestBody @Valid @NotNull City city) {
        cityService.save(city);
        return ResponseEntity.ok().body(city);
    }

    @GetMapping(value = "/city_by_cityName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> getByCityName(@RequestParam("cityName") String cityName){
        return cityService.getIterableCityByCityName(cityName);
    }


    @RequestMapping(value = "/city",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull City city) {
        Optional<City> genre1 = cityService.getById(city.getId());
        if (Objects.nonNull(genre1)) {
            cityService.save(city);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/cities",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> redirect(Model model) {
        return cityService.getAll();
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        cityService.delete(id);
        return new RedirectView("/api/cities",true);
    }


}
