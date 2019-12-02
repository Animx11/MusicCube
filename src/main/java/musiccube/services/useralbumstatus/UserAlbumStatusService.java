package musiccube.services.useralbumstatus;

import musiccube.entities.UserAlbumStatus;

public interface UserAlbumStatusService {
    Iterable<UserAlbumStatus> getOwnedByUserId(int id);
    Iterable<UserAlbumStatus> getSoughtByUserId(int id);
}
