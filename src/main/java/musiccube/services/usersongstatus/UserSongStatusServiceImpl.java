package musiccube.services.usersongstatus;

import musiccube.entities.UserSongStatus;
import musiccube.repositories.UserSongStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSongStatusServiceImpl implements UserSongStatusService {
    @Autowired
    private UserSongStatusRepo repo;

    @Override
    public Optional<UserSongStatus> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public Iterable<UserSongStatus> getListenedByUserName(String userName) {
        return repo.findByUserUserNameAndListened(userName, true);
    }

    @Override
    public Iterable<UserSongStatus> getToListenByUserName(String userName) {
        return repo.findByUserUserNameAndToListen(userName, true);
    }

    @Override
    public UserSongStatus save(UserSongStatus songStatus) {
        return repo.save(songStatus);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
