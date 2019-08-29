package musiccube.controllers;

import musiccube.entities.Role;
import musiccube.entities.RoleName;
import musiccube.entities.User;
import musiccube.entities.UserFavorites;
import musiccube.repositories.RoleRepository;
import musiccube.services.user.UserService;
import musiccube.services.userFavorites.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="${serverAddress}")
public class ModelGenerator {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserFavoritesService userFavoritesService;


    @RequestMapping(value = "generateModel", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel() {

        Role user = new Role(RoleName.ROLE_USER);
        Role mod = new Role(RoleName.ROLE_MOD);
        Role admin = new Role(RoleName.ROLE_ADMIN);

        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(user);
        rolesAdmin.add(mod);
        rolesAdmin.add(admin);

        Set<Role> rolesMod = new HashSet<>();
        rolesMod.add(user);
        rolesMod.add(mod);

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(user);

        User userUser = new User("user",passwordEncoder.encode("user12"), "user", rolesUser);
        User modUser = new User("mod",passwordEncoder.encode("mod123"), "mod", rolesMod);
        User adminUser = new User("admin",passwordEncoder.encode("admin1"), "admin", rolesAdmin);

        userUser.setFirstName("Dawid");
        userUser.setLastName("Buczyński");
        userUser.setAboutUser("Just a normal user");

        UserFavorites userFavorites1 = new UserFavorites(adminUser);
        UserFavorites userFavorites2 = new UserFavorites(modUser);
        UserFavorites userFavorites3 = new UserFavorites(userUser);

        roleRepository.save(user);
        roleRepository.save(mod);
        roleRepository.save(admin);

        userService.save(adminUser);
        userService.save(userUser);
        userService.save(modUser);

        userFavoritesService.save(userFavorites1);
        userFavoritesService.save(userFavorites2);
        userFavoritesService.save(userFavorites3);

        return "Model generated.";
    }
}
