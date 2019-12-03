package musiccube.controllers;

import musiccube.entities.UserSongStatus;
import musiccube.services.usersongstatus.UserSongStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "${serverAddress}")
public class UserSongStatusController {
    @Autowired
    private UserSongStatusService statusService;


    @GetMapping(
            path = "/listened-songs",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserSongStatus> getListenedSongs(@RequestParam(name = "user") String userName) {
        return statusService.getListenedByUserName(userName);
    }
    @GetMapping(
            path = "/songs-to-listen",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserSongStatus> getSongsToListen(@RequestParam(name = "user") String userName) {
        return statusService.getToListenByUserName(userName);

    }

    @PostMapping("/song-status")
    public ResponseEntity<UserSongStatus> create(UserSongStatus status) {
        statusService.save(status);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/song-status")
    public ResponseEntity<Void> edit(UserSongStatus status) {
        Optional<UserSongStatus> oldStatus = statusService.getById(status.getId());
        if (oldStatus.isPresent()) {
            statusService.save(status);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/song-status/{id}")
    public void delete(@PathVariable("id") int id) {
        if (statusService.getById(id).isPresent()) {
            statusService.delete(id);
        }
    }
}
