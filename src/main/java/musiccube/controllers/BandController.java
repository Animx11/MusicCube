package musiccube.controllers;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Genre;
import musiccube.services.band.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;


import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class BandController {

    @Autowired
    private BandService bandService;

/******************************** GET ***************************************/

    @GetMapping(
            path = "/band/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Band> getById(@PathVariable("id") int id) {
        Optional<Band> band = bandService.getById(id);
        return band.isPresent() ?
                ResponseEntity.ok(band.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/band",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getAll() {
        return bandService.getAll();
    }

    // --- Get all bands with paging ---
    @GetMapping(
            path = "band/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {
        return perPage == null ?
                bandService.getAllPaging(pageNr, Defaults.PAGESIZE) :
                bandService.getAllPaging(pageNr, perPage);
    }

    // --- Band by name ---
    @GetMapping(
            path = "/band/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> getByBandName(@PathVariable("name") String bandName) {
        return bandService.getByBandName(bandName);
    }

    // --- Genres ---
    @GetMapping(
            path = "/band/{id}/genres",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Genre> getBandGenres(@PathVariable("id") int bandId) {
        return bandService.getBandGenres(bandId);
    }

    // --- Albums ---
    @GetMapping(
            path = "/band/{id}/albums",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Album> getBandAlbums(@PathVariable("id") int bandId) {
        return bandService.getBandAlbums(bandId);
    }

/*******************************************************************************/

    @PostMapping("/admin/band")
    public ResponseEntity<Band> create(@RequestBody @Valid @NotNull Band band) {
        bandService.save(band);
        return ResponseEntity.ok().body(band);
    }

    @PutMapping("/admin/band")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Band band) {
        Optional<Band> band1 = bandService.getById(band.getId());
        if (Objects.nonNull(band1)) {
            bandService.save(band);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiIgnore
    @DeleteMapping(
            path = "/admin/band",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Band> redirect(Model model) {
        return bandService.getAll();
    }

    @DeleteMapping(path = "/admin/band/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        bandService.delete(id);
        return new RedirectView("/api/admin/band",true);
    }

}
