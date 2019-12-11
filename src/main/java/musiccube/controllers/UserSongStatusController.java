package musiccube.controllers;

import musiccube.dtos.UserSongStatusDto;
import musiccube.entities.UserSongStatus;
import musiccube.services.usersongstatus.UserSongStatusService;
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

    @GetMapping(
            path = "/song-status",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserSongStatus> getByUserAndSong(
            @RequestParam(name = "user") String userName,
            @RequestParam(name = "song") int songId
    ) {
        Optional<UserSongStatus> status = statusService.getByUserAndSong(userName, songId);
        if (status.isPresent()) {
            return ResponseEntity.ok(status.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/song-status")
    public ResponseEntity<UserSongStatus> edit(@RequestBody @Valid @NotNull UserSongStatusDto statusDto) {
        try {
            return ResponseEntity.status(201).body(statusService.save(statusDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/song-status/{id}")
    public void delete(@PathVariable("id") int id) {
        if (statusService.getById(id).isPresent()) {
            statusService.delete(id);
        }
    }
}
