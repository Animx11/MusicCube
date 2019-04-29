package MusicCube.services.song;

import MusicCube.entities.Song;
import MusicCube.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Optional<Song> getById(int id) {
        return songRepository.findById(id);
    }
    @Override
    public Iterable<Song> getAll() {
        return songRepository.findAll();
    }
    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }
    @Override
    public void delete(int id) {
        songRepository.deleteById(id);
    }
    @Override
    public Iterable<Song> getByName(String name) {
        return songRepository.findByName(name);
    }
}
