package musiccube.controllers;

import musiccube.entities.UserSongStatus;
import musiccube.services.usersongstatus.UserSongStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class UserSongStatusController {
    @Autowired
    private UserSongStatusService statusService;


    @GetMapping(
            path = "/user/listened-songs/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserSongStatus> getListenedSongs(@PathVariable(name = "id") int userId) {
        return statusService.getListenedByUserId(userId);
    }
    @GetMapping(
            path = "/user/songs-to-listen/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserSongStatus> getSongsToListen(@PathVariable(name = "id") int userId) {
        return statusService.getToListenByUserId(userId);

    }
}
