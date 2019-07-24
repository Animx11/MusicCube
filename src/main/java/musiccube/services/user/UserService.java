package musiccube.services.user;

import musiccube.entities.Role;
import musiccube.entities.RoleName;
import musiccube.entities.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    void delete(Integer id);

    Optional<User> getById(int id);
    Optional<User> getByUserName(String userName);
    String getPasswordByUserName(String userName);
    Optional<Role> getByName(RoleName roleName);
    Iterable<User> listUsers();

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
