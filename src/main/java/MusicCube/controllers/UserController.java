package MusicCube.controllers;


import MusicCube.cipher.EncrypterAES;
import MusicCube.entities.AuthorisationToken;
import MusicCube.entities.Role;
import MusicCube.entities.RoleName;
import MusicCube.entities.User;
import MusicCube.jwt.JwtProvider;
import MusicCube.services.user.UserService;
import MusicCube.jwt.TokenCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;



    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@RequestParam("id") Integer id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users_by_id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@RequestParam("id") int id){
        return userService.getById(id);
    }

    @RequestMapping(value = "/users_by_userName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getByUserName(@RequestParam("userName") String userName){
        return userService.getByUserName(userName);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> listUsers(){
        return userService.listUsers();
    }

/*
    @RequestMapping(value = "/signIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorisationToken> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password){

        EncrypterAES encrypterAES = new EncrypterAES();

        String encryptedPassword = encrypterAES.encrypt(password);

        if(encryptedPassword.equals(userService.getPasswordByUserName(userName))){

            RestTemplate restTemplate = new RestTemplate();
            TokenCreator tokenCreator = new TokenCreator();

            AuthorisationToken authorisationToken = tokenCreator.create(userName, getUserPermissionByUserName(userName));

            return restTemplate.postForEntity("http://localhost:8080/api/token", authorisationToken, AuthorisationToken.class);

        }
        else{
            return null;
        }
    }
*/

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody @Valid @NotNull User user){

        if(userService.existsByUserName(user.getUserName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(user.getEmail())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
/*
        Role role = new Role();
        role.setName(RoleName.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
*/
        userService.save(user);

        return ResponseEntity.ok().body(user);
    }

    /*
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody @Valid @NotNull User user){
        //Set<Role> roles = new HashSet<>();
        //Role role = new Role(RoleName.ROLE_USER);
        //user.setRoles();
        EncrypterAES encrypterAES = new EncrypterAES();

        String encryptedPassword;
        encryptedPassword = encrypterAES.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

        userService.save(user);
        return ResponseEntity.ok().body(user);
    }
*/

}
