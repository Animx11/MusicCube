package MusicCube.Controllers;


import MusicCube.Entities.Users;
import MusicCube.Services.UsersServices.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Users> create(@RequestBody @Valid @NotNull Users user){
        usersService.save(user);
        return ResponseEntity.ok().body(user);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<Users> delete(@RequestParam("id") Integer id){
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users_by_id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users getById(@RequestParam("id") int id){
        return usersService.getById(id);
    }

    @RequestMapping(value = "/users_by_userName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Users> getByUserName(@RequestParam("userName") String userName){
        return usersService.getByUserName(userName);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Users> listUsers(){
        return usersService.listUsers();
    }

}
