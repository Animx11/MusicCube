package musiccube.repositories;

import musiccube.entities.UserSongStatus;
import org.springframework.data.repository.CrudRepository;

public interface UserSongStatusRepo extends CrudRepository<UserSongStatus, Integer> {

    Iterable<UserSongStatus> findByUserUserNameAndListened(String userName, boolean b);

    Iterable<UserSongStatus> findByUserUserNameAndToListen(String userName, boolean b);
}
