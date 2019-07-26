package MusicCube.services.user;

import MusicCube.entities.Role;
import MusicCube.entities.RoleName;
import MusicCube.entities.User;
import MusicCube.user.UserProfile;

import java.util.Optional;

public interface UserService {

    User save(User user);
    void delete(Integer id);

    Optional<User> getById(int id);
    Optional<User> getByUserName(String userName);
    Iterable<User> listUsers();

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
