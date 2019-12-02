package musiccube.services.useralbumstatus;

import musiccube.entities.UserAlbumStatus;
import musiccube.repositories.UserAlbumStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAlbumStatusServiceImpl implements UserAlbumStatusService {
    @Autowired
    private UserAlbumStatusRepo repository;

    @Override
    public Iterable<UserAlbumStatus> getOwnedByUserId(int id) {
        return repository.findByUserIdAndOwned(id, true);
    }

    @Override
    public Iterable<UserAlbumStatus> getSoughtByUserId(int id) {
        return repository.findByUserIdAndSought(id, true);
    }
}
