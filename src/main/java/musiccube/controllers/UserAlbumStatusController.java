package musiccube.controllers;

import musiccube.entities.UserAlbumStatus;
import musiccube.services.useralbumstatus.UserAlbumStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/album-status")
    public ResponseEntity<UserAlbumStatus> create(UserAlbumStatus status) {
        statusService.save(status);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/album-status")
    public ResponseEntity<Void> edit(UserAlbumStatus status) {
        Optional<UserAlbumStatus> oldStatus = statusService.getById(status.getId());
        if (oldStatus.isPresent()) {
            statusService.save(status);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/album-status/{id}")
    public void delete(@PathVariable("id") int id) {
        if (statusService.getById(id).isPresent()) {
            statusService.delete(id);
        }
    }
}
