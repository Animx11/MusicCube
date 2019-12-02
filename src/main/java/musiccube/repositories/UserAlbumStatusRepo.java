package musiccube.repositories;

import musiccube.entities.UserAlbumStatus;
import org.springframework.data.repository.CrudRepository;

public interface UserAlbumStatusRepo extends CrudRepository<UserAlbumStatus, Integer> {

    public Iterable<UserAlbumStatus> findByUserIdAndOwned(int userId, boolean owned);
    public Iterable<UserAlbumStatus> findByUserIdAndSought(int userId, boolean sought);
}
