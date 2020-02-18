package musiccube.services.usersongstatus;

import musiccube.dtos.UserSongStatusDto;
import musiccube.entities.Song;
import musiccube.entities.User;
import musiccube.entities.UserSongStatus;
import musiccube.repositories.SongRepository;
import musiccube.repositories.UserRepository;
import musiccube.repositories.UserSongStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSongStatusServiceImpl implements UserSongStatusService {
    @Autowired
    private UserSongStatusRepo repo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SongRepository songRepository;


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
    public Optional<UserSongStatus> getByUserAndSong(String userName, int songId) {
        return Optional.empty();
    }

    @Override
    public UserSongStatus save(UserSongStatusDto statusDto) {
        UserSongStatus newStatus;

        Optional<UserSongStatus> optStatus = repo.findOneByUserUserNameAndSongId(
                statusDto.getUserName(),
                statusDto.getSongId()
        );

        if (optStatus.isPresent()) {

            newStatus = optStatus.get();
            newStatus.setListened(statusDto.isListened());
            newStatus.setToListen(statusDto.isToListen());

        } else {

            newStatus = new UserSongStatus();

            Optional<User> optUser = userRepository.findOneByUserName(statusDto.getUserName());
            if (!optUser.isPresent()) { throw new IllegalArgumentException("no such user"); }
            Optional<Song> optSong = songRepository.findById(statusDto.getSongId());
            if (!optSong.isPresent()) { throw new IllegalArgumentException("no such song"); }

            newStatus.setUser(optUser.get());
            newStatus.setSong(optSong.get());
            newStatus.setToListen(statusDto.isToListen());
            newStatus.setListened(statusDto.isListened());

        }
        repo.save(newStatus);
        return newStatus;
    }


    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
