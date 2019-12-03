package musiccube.services.usersongstatus;

import musiccube.entities.UserSongStatus;

import java.util.Optional;

public interface UserSongStatusService {
    UserSongStatus save(UserSongStatus songStatus);
    void delete(int id);

    Optional<UserSongStatus> getById(int id);

    Iterable<UserSongStatus> getListenedByUserName(String userName);

    Iterable<UserSongStatus> getToListenByUserName(String userName);
}
