package musiccube.repositories;

import musiccube.entities.UserSongStatus;
import org.springframework.data.repository.CrudRepository;

public interface UserSongStatusRepo extends CrudRepository<UserSongStatus, Integer> {

    Iterable<UserSongStatus> findByUserNameAndListened(String userName, boolean b);

    Iterable<UserSongStatus> findByUserNameAndToListen(String userName, boolean b);
}
