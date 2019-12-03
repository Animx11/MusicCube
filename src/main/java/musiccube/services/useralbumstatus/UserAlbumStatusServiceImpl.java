package musiccube.services.useralbumstatus;

import musiccube.entities.UserAlbumStatus;
import musiccube.repositories.UserAlbumStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAlbumStatusServiceImpl implements UserAlbumStatusService {
    @Autowired
    private UserAlbumStatusRepo repository;

    @Override
    public Optional<UserAlbumStatus> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserAlbumStatus> getSoughtByUserName(String userName) {
        return repository.findByUserNameAndSought(userName, true);
    }

    @Override
    public Iterable<UserAlbumStatus> getOwnedByUserName(String userName) {
        return repository.findByUserNameAndOwned(userName, true);
    }

    @Override
    public UserAlbumStatus save(UserAlbumStatus status) {
        return repository.save(status);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
