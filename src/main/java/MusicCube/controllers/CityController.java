package MusicCube.controllers;

import MusicCube.entities.City;
import MusicCube.services.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/city{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<City> getById(int id) {
        return cityService.getById(id);
    }

    @RequestMapping(value = "/countries",
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

    @RequestMapping(value = "/city",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull City city) {
        Optional<City> genre1 = cityService.getById(city.getId());
        if (Objects.nonNull(genre1)) {
            cityService.save(city);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/countries",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<City> redirect(Model model) {
        return cityService.getAll();
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        cityService.delete(id);
        return new RedirectView("/api/genres",true);
    }


}
