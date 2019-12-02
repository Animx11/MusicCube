package musiccube.repositories;

import musiccube.entities.UserSongStatus;
import org.springframework.data.repository.CrudRepository;

public interface UserSongStatusRepo extends CrudRepository<UserSongStatus, Integer> {
    public Iterable<UserSongStatus> findByUserIdAndListened(int userId, boolean listened);
    public Iterable<UserSongStatus> findByUserIdAndToListen(int userId, boolean toListen);
}
