package musiccube.controllers;

import musiccube.entities.Country;
import musiccube.services.country.CountryService;
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
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(
            path = "/country/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Country> getById(int id) {
        Optional<Country> country = countryService.getById(id);
        return country.isPresent() ?
                ResponseEntity.ok(country.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/country",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Country> getAll() {
        return countryService.getAll();
    }

    // --- Get by name ---
    @GetMapping(
            path = "/country/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Country> getByCountryName(@PathVariable("name") String countryName) {
        return countryService.getByCountryName(countryName);
    }

    // --- Get by code ---
    @GetMapping(
            path = "/country/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Country> getByCode(@PathVariable("code") String code) {
        return countryService.getByCode(code);
    }

    @PostMapping("/admin/country")
    public ResponseEntity<Country> create(@RequestBody @Valid @NotNull Country country) {
        countryService.save(country);
        return ResponseEntity.ok().body(country);
    }

    @PutMapping("/admin/country")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Country country) {
        Optional<Country> genre1 = countryService.getById(country.getId());
        if (Objects.nonNull(genre1)) {
            countryService.save(country);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @DeleteMapping(
            path = "/admin/country",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Country> redirect(Model model) {
        return countryService.getAll();
    }

    @DeleteMapping(path = "/admin/country/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        countryService.delete(id);
        return new RedirectView("/api/admin/country",true);
    }


}
