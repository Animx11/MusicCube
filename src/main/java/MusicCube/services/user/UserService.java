package MusicCube.services.user;

import MusicCube.entities.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    void delete(Integer id);

    User getById(int id);
    Optional<User> getByUserName(String userName);
    String getPasswordByUserName(String userName);
    String getUserPermissionByUserName(String userName);
    Iterable<User> listUsers();

}
