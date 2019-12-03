package musiccube.repositories;

import musiccube.entities.UserAlbumStatus;
import org.springframework.data.repository.CrudRepository;

public interface UserAlbumStatusRepo extends CrudRepository<UserAlbumStatus, Integer> {

    Iterable<UserAlbumStatus> findByUserNameAndOwned(String userName, boolean b);

    Iterable<UserAlbumStatus> findByUserNameAndSought(String userName, boolean b);
}
