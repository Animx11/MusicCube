package MusicCube.services.songauthor;

import MusicCube.entities.SongAuthor;
import MusicCube.repositories.SongAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongAuthorServiceImpl implements SongAuthorService {

    @Autowired
    private SongAuthorRepository songAuthorRepository;

    @Override
    public Optional<SongAuthor> getById(int id) {
        return songAuthorRepository.findById(id);
    }
    @Override
    public Iterable<SongAuthor> getAll() {
        return songAuthorRepository.findAll();
    }
    @Override
    public SongAuthor save(SongAuthor songAuthor) {
        return songAuthorRepository.save(songAuthor);
    }
    @Override
    public void delete(int id) {
        songAuthorRepository.deleteById(id);
    }
}
