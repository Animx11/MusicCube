package musiccube.services.usersongstatus;

import musiccube.entities.UserSongStatus;

public interface UserSongStatusService {
    Iterable<UserSongStatus> getListenedByUserId(int id);
    Iterable<UserSongStatus> getToListenByUserId(int id);
}
