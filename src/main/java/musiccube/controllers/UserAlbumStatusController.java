package musiccube.controllers;

import musiccube.entities.UserAlbumStatus;
import musiccube.services.useralbumstatus.UserAlbumStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class UserAlbumStatusController {
    @Autowired
    private UserAlbumStatusService statusService;

    @GetMapping (
            path = "/user/owned-albums/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserAlbumStatus> getOwnedAlbums(@PathVariable(name = "id") int userId) {
        return statusService.getOwnedByUserId(userId);
    }

    @GetMapping (
            path = "/user/sought-albums/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<UserAlbumStatus> getSoughtAlbums(@PathVariable(name = "id") int userId) {
        return statusService.getSoughtByUserId(userId);
    }
}
