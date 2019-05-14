package MusicCube.services.user;

import MusicCube.entities.User;

public interface UserService {

    User save(User user);
    void delete(Integer id);

    User getById(int id);
    Iterable<User> getByUserName(String userName);
    String getPasswordByUserName(String userName);
    String getUserPermissionByUserName(String userName);
    Iterable<User> listUsers();

}
