package musiccube.repositories;

import musiccube.entities.UserAlbumStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAlbumStatusRepo extends CrudRepository<UserAlbumStatus, Integer> {

    Iterable<UserAlbumStatus> findByUserUserNameAndOwned(String userName, boolean b);

    Iterable<UserAlbumStatus> findByUserUserNameAndSought(String userName, boolean b);

    Optional<UserAlbumStatus> findOneByUserUserNameAndAlbumId(String userName, int albumId);
}
