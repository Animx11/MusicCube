package musiccube.controllers;

import musiccube.entities.Localization;
import musiccube.services.localization.LocalizationService;
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
public class LocalizationController {

    @Autowired
    private LocalizationService localizationService;

//------------- GETs ------------------
    @RequestMapping(value = "/localization{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Localization> getById(int id) {
        return localizationService.getById(id);
    }

    @RequestMapping(value = "/localizations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Localization> getAll() {
        return localizationService.getAll();
    }

    @RequestMapping(value = "/localization{country}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Localization> getByCountry(String country) { return localizationService.getByCountry(country); }
    @RequestMapping(value = "/localization{city}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Localization> getByCity(String city) { return localizationService.getByCity(city); }
    @RequestMapping(value = "/localization{address}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Localization> getByAddress(String address) { return localizationService.getByAddress(address); }
    @RequestMapping(value = "/localization{anything}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Localization> getByAnything(String input) { return localizationService.getByAnything(input); }

    //----------------------------------------

    @RequestMapping(value = "/localization",method = RequestMethod.POST)
    public ResponseEntity<Localization> create(@RequestBody @Valid @NotNull Localization localization) {
        localizationService.save(localization);
        return ResponseEntity.ok().body(localization);
    }

    @RequestMapping(value = "/localization",method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Localization localization) {
        Optional<Localization> localization1 = localizationService.getById(localization.getId());
        if (Objects.nonNull(localization1)) {
            localizationService.save(localization);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @RequestMapping(value = "/localizations",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Localization> redirect(Model model) {
        return localizationService.getAll();
    }

    @RequestMapping(value = "/localization/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        localizationService.delete(id);
        return new RedirectView("/api/localizations",true);
    }



}
