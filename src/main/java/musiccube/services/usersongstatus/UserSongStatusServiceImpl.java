package musiccube.services.usersongstatus;

import musiccube.entities.UserSongStatus;
import musiccube.repositories.UserSongStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSongStatusServiceImpl implements UserSongStatusService {
    @Autowired
    private UserSongStatusRepo repo;

    @Override
    public Iterable<UserSongStatus> getListenedByUserId(int id) {
        return repo.findByUserIdAndListened(id,true);
    }

    @Override
    public Iterable<UserSongStatus> getToListenByUserId(int id) {
        return repo.findByUserIdAndToListen(id, true);
    }
}
