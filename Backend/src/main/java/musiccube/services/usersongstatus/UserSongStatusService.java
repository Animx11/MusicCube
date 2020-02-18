package musiccube.services.usersongstatus;

import musiccube.dtos.UserSongStatusDto;
import musiccube.entities.UserSongStatus;

import java.util.Optional;

public interface UserSongStatusService {
    UserSongStatus save(UserSongStatusDto songStatusDto);
    void delete(int id);

    Optional<UserSongStatus> getById(int id);

    Iterable<UserSongStatus> getListenedByUserName(String userName);

    Iterable<UserSongStatus> getToListenByUserName(String userName);

    Optional<UserSongStatus> getByUserAndSong(String userName, int songId);
}
