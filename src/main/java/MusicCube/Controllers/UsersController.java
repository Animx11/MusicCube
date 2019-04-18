package MusicCube.Controllers;


import MusicCube.Cipher.EncrypterAES;
import MusicCube.Entities.AuthorisationToken;
import MusicCube.Entities.Users;
import MusicCube.Services.Users.UsersService;
import MusicCube.TockenCreator.TokenCreator;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Users> create(@RequestBody @Valid @NotNull Users user){

        user.setUserPermission("user");
        EncrypterAES encrypterAES = new EncrypterAES();

        String encryptedPassword;
        encryptedPassword = encrypterAES.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

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

    @RequestMapping(value = "/users_permission_by_userName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserPermissionByUserName(@RequestParam("userName") String userName){
        return usersService.getUserPermissionByUserName(userName);
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorisationToken> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password){

        EncrypterAES encrypterAES = new EncrypterAES();

        String encryptedPassword = encrypterAES.encrypt(password);

        if(encryptedPassword.equals(usersService.getPasswordByUserName(userName))){

            RestTemplate restTemplate = new RestTemplate();
            TokenCreator tokenCreator = new TokenCreator();

            AuthorisationToken authorisationToken = tokenCreator.create(userName, getUserPermissionByUserName(userName));

            return restTemplate.postForEntity("http://localhost:8080/api/token", authorisationToken, AuthorisationToken.class);

        }
        else{
            return null;
        }
    }

}
