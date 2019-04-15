package MusicCube.controllers;

import MusicCube.entities.Location;
import MusicCube.services.location.LocationService;
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
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/location{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Location> getById(int id) {
        return locationService.getById(id);
    }

    @RequestMapping(value = "/locations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Location> getAll() {
        return locationService.getAll();
    }

    @RequestMapping(value = "/location",method = RequestMethod.POST)
    public ResponseEntity<Location> create(@RequestBody @Valid @NotNull Location location) {
        locationService.save(location);
        return ResponseEntity.ok().body(location);
    }

    @RequestMapping(value = "/location",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Location location) {
        Optional<Location> location1 = locationService.getById(location.getId());
        if (Objects.nonNull(location1)) {
            locationService.save(location);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/locations",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Location> redirect(Model model) {
        return locationService.getAll();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        locationService.delete(id);
        return new RedirectView("/api/locations",true);
    }



}
