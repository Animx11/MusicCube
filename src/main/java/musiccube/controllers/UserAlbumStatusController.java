package musiccube.controllers;

import musiccube.dtos.UserAlbumStatusDto;
import musiccube.entities.UserAlbumStatus;
import musiccube.services.useralbumstatus.UserAlbumStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "${serverAddress}")
public class UserAlbumStatusController {
    @Autowired
    private UserAlbumStatusService statusService;

    @GetMapping (
            path = "/owned-albums",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserAlbumStatus> getOwnedAlbums(@RequestParam(name = "user") String userName) {
        return statusService.getOwnedByUserName(userName);
    }

    @GetMapping (
            path = "/sought-albums",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserAlbumStatus> getSoughtAlbums(@RequestParam(name = "user") String userName) {
        return statusService.getSoughtByUserName(userName);
    }

    @GetMapping(
            path = "/album-status",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserAlbumStatus> getByUserAndAlbum(
            @RequestParam(name = "user") String userName,
            @RequestParam(name = "album") int albumId
    ) {
        Optional<UserAlbumStatus> status = statusService.getByUserAndAlbum(userName, albumId);
        if (status.isPresent()) {
            return ResponseEntity.ok(status.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/album-status")
    public ResponseEntity<UserAlbumStatus> edit(@RequestBody @Valid @NotNull UserAlbumStatusDto statusDto) {
        try {
            return ResponseEntity.status(201).body(statusService.save(statusDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/album-status/{id}")
    public void delete(@PathVariable("id") int id) {
        if (statusService.getById(id).isPresent()) {
            statusService.delete(id);
        }
    }
}
