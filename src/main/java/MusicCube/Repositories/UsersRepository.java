package MusicCube.Repositories;

import MusicCube.Entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {

    @Query("SELECT u from Users u where u.id = ?1")
    Users findById(int id);

    @Query("SELECT u from Users u where u.userName like ?1")
    Iterable<Users> findByUserName(String userName);

}
