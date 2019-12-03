package musiccube.services.useralbumstatus;

import musiccube.entities.UserAlbumStatus;

import java.util.Optional;

public interface UserAlbumStatusService {
    Optional<UserAlbumStatus> getById(int id);
    UserAlbumStatus save(UserAlbumStatus status);
    void delete(int id);

    Iterable<UserAlbumStatus> getSoughtByUserName(String userName);

    Iterable<UserAlbumStatus> getOwnedByUserName(String userName);
}
