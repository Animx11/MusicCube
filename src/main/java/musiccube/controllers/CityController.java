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

    @ApiIgnore
    @DeleteMapping(
            path = "/admin/city",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<City> redirect(Model model) {
        return cityService.getAll();
    }

    @DeleteMapping("/city/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        cityService.delete(id);
        return new RedirectView("/api/admin/city",true);
    }


}
