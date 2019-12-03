package musiccube.services.useralbumstatus;

import musiccube.dtos.UserAlbumStatusDto;
import musiccube.entities.UserAlbumStatus;

import java.util.Optional;

public interface UserAlbumStatusService {
    Optional<UserAlbumStatus> getById(int id);
    UserAlbumStatus save(UserAlbumStatusDto statusDto);
    void delete(int id);

    Iterable<UserAlbumStatus> getSoughtByUserName(String userName);

    Iterable<UserAlbumStatus> getOwnedByUserName(String userName);

    Optional<UserAlbumStatus> getByUserAndAlbum(String userName, int albumId);
}
