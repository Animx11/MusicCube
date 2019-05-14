package MusicCube.repositories;

import MusicCube.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u from User u where u.id = ?1")
    User findById(int id);

    @Query("SELECT u from User u where u.userName like ?1")
    Iterable<User> findByUserName(String userName);

    @Query("select password from User u where u.userName like ?1")
    String findPasswordByUserName(String userName);

    @Query("select userPermission from User u where u.userName like ?1")
    String findUSerPermissionByUserName(String userName);

}
