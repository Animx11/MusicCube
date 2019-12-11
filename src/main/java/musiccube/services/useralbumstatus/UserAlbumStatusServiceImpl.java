package musiccube.services.useralbumstatus;

import musiccube.dtos.UserAlbumStatusDto;
import musiccube.entities.Album;
import musiccube.entities.User;
import musiccube.entities.UserAlbumStatus;
import musiccube.repositories.AlbumRepository;
import musiccube.repositories.UserAlbumStatusRepo;
import musiccube.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAlbumStatusServiceImpl implements UserAlbumStatusService {
    @Autowired
    private UserAlbumStatusRepo repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Optional<UserAlbumStatus> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserAlbumStatus> getSoughtByUserName(String userName) {
        return repository.findByUserUserNameAndSought(userName, true);
    }

    @Override
    public Iterable<UserAlbumStatus> getOwnedByUserName(String userName) {
        return repository.findByUserUserNameAndOwned(userName, true);
    }

    @Override
    public UserAlbumStatus save(UserAlbumStatusDto statusDto) {
        UserAlbumStatus newStatus;

        Optional<UserAlbumStatus> optStatus = repository.findOneByUserUserNameAndAlbumId(
                statusDto.getUserName(),
                statusDto.getAlbumId()
        );

        if (optStatus.isPresent()) {

            newStatus = optStatus.get();
            newStatus.setOwned(statusDto.isOwned());
            newStatus.setSought(statusDto.isSought());

        } else {

            newStatus = new UserAlbumStatus();

            Optional<User> optUser = userRepository.findByUserName(statusDto.getUserName());
            if (!optUser.isPresent()) { throw new IllegalArgumentException("no such user"); }
            Optional<Album> optAlbum = albumRepository.findById(statusDto.getAlbumId());
            if (!optAlbum.isPresent()) { throw new IllegalArgumentException("no such album"); }

            newStatus.setUser(optUser.get());
            newStatus.setAlbum(optAlbum.get());
            newStatus.setSought(statusDto.isSought());
            newStatus.setOwned(statusDto.isOwned());

        }
        repository.save(newStatus);
        return newStatus;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserAlbumStatus> getByUserAndAlbum(String userName, int albumId) {
        return repository.findOneByUserUserNameAndAlbumId(userName, albumId);
    }
}
