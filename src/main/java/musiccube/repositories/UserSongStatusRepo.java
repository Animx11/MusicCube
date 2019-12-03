package musiccube.repositories;

import musiccube.entities.UserSongStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserSongStatusRepo extends CrudRepository<UserSongStatus, Integer> {

    Iterable<UserSongStatus> findByUserUserNameAndListened(String userName, boolean b);

    Iterable<UserSongStatus> findByUserUserNameAndToListen(String userName, boolean b);
    
    Optional<UserSongStatus> findOneByUserUserNameAndSongId(String userName, int songId);
}
