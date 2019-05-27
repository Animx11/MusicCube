package MusicCube.controllers;

import MusicCube.entities.Country;
import MusicCube.services.country.CountryService;
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
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/country{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Country> getById(int id) {
        return countryService.getById(id);
    }

    @RequestMapping(value = "/countries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> getAll() {
        return countryService.getAll();
    }


    @RequestMapping(value = "/country_name{countryName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> getByCountryName(String countryName) {
        return countryService.getByCountryName(countryName);
    }

    @RequestMapping(value = "/country_code{code}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> getByCode(String code) {
        return countryService.getByCode(code);
    }

    @RequestMapping(value = "/country",method = RequestMethod.POST)
    public ResponseEntity<Country> create(@RequestBody @Valid @NotNull Country country) {
        countryService.save(country);
        return ResponseEntity.ok().body(country);
    }

    @RequestMapping(value = "/country",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Country country) {
        Optional<Country> genre1 = countryService.getById(country.getId());
        if (Objects.nonNull(genre1)) {
            countryService.save(country);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/countries",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Country> redirect(Model model) {
        return countryService.getAll();
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        countryService.delete(id);
        return new RedirectView("/api/genres",true);
    }


}
