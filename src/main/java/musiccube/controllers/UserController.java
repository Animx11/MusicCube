package musiccube.controllers;


import musiccube.entities.Role;
import musiccube.entities.RoleName;
import musiccube.entities.User;
import musiccube.jwt.JwtProvider;
import musiccube.jwt.JwtResponse;
import musiccube.repositories.RoleRepository;
import musiccube.services.user.UserService;
import musiccube.user.UserAccount;
import musiccube.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest httpRequest;

    private String getJwt(HttpServletRequest httpRequest){

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;

    }

    // Delete User

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user")
    public ResponseEntity<User> delete(@RequestParam("id") Integer id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Finding User

    @DeleteMapping(value = "/user_by_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getById(@RequestParam("id") int id){
        return userService.getById(id);
    }

    @GetMapping(value = "/user_by_userName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getByUserName(@RequestParam("userName") String userName){
        return userService.getByUserName(userName);
    }

    // Edit User

    @PutMapping(value = "/edit")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull User user) {

        User takeUser = userService.getById(user.getId()).orElse(null);

        if(takeUser != null){
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // User Profile


    @GetMapping(value = "/userProfile_by_userName", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<UserProfile> getProfileByUserName(@RequestParam("userName") String userName){
        User user = userService.getByUserName(userName).orElse(new User());
        UserProfile userProfile = new UserProfile(user);
        return Optional.of(userProfile);
    }

    @PutMapping(value = "/edit_userProfile")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull UserProfile userProfile) {

        User takeUser = userService.getById(userProfile.getId()).orElse(null);
        if(jwtProvider.validateJwt(getJwt(httpRequest))) {
            if (takeUser != null) {

                takeUser.setFirstName(userProfile.getFirstName());
                takeUser.setLastName(userProfile.getLastName());
                takeUser.setBirthDate(userProfile.getBirthDate());
                takeUser.setAboutUser(userProfile.getAboutUser());
                userService.save(takeUser);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }

            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // User Account

    @PutMapping(value = "/edit_userName")
    public ResponseEntity<?> editUserName(@RequestParam("newUserName") String newUserName, @RequestBody @Valid @NotNull UserAccount userAccount){
        User takeUser = userService.getByUserName(userAccount.getUserName()).orElse(null);
        if(jwtProvider.validateJwt(getJwt(httpRequest))) {
            if (takeUser != null) {
                if (!userService.existsByUserName(newUserName)) {
                    takeUser.setUserName(newUserName);
                    userService.save(takeUser);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }

            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "edit_email")
    public ResponseEntity<?> editEmail(@RequestBody @Valid @NotNull UserAccount userAccount){
        User takeUser = userService.getByUserName(userAccount.getUserName()).orElse(null);
        if(jwtProvider.validateJwt(getJwt(httpRequest))) {
            if(takeUser != null) {
                if(!userService.existsByEmail(userAccount.getEmail())){
                    takeUser.setEmail(userAccount.getEmail());
                    userService.save(takeUser);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/edit_password")
    public ResponseEntity<?> editPassword(@RequestParam("newPassword") String newPassword, @RequestBody @Valid @NotNull UserAccount userAccount){
        User takeUser = userService.getByUserName(userAccount.getUserName()).orElse(null);
        if(jwtProvider.validateJwt(getJwt(httpRequest))) {
            if (takeUser != null) {
                if (passwordEncoder.matches(userAccount.getPassword(), takeUser.getPassword())) {
                    takeUser.setPassword(passwordEncoder.encode(newPassword));
                    userService.save(takeUser);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // User List

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> listUsers(){
        return userService.listUsers();
    }

    // User sign in/out

    @PostMapping(value = "/auth/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody @Valid @NotNull UserAccount userAccount){

        User user = new User();

        if(userService.existsByUserName(userAccount.getUserName())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (userService.existsByEmail(userAccount.getEmail())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Fail! -> User Role not found"));

        roles.add(role);

        user.setRoles(roles);
        user.setUserName(userAccount.getUserName());
        user.setEmail(userAccount.getEmail());
        user.setPassword(userAccount.getPassword());

        userService.save(user);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/auth/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody @Valid @NotNull UserAccount userAccount){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAccount.getUserName(), userAccount.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwt(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

    }


}
