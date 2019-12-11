package musiccube.services.user;

import musiccube.entities.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    void delete(Integer id);

    Optional<User> getById(int id);
    Optional<User> getByUserName(String userName);
    Iterable<User> listUsers();

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsById(int id);

}
