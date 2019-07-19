package MusicCube.services.songauthorship;

import MusicCube.entities.Person;
import MusicCube.entities.Song;
import MusicCube.entities.SongAuthorship;
import MusicCube.repositories.SongAuthorshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongAuthorshipServiceImpl implements SongAuthorshipService {

    @Autowired
    private SongAuthorshipRepository songAuthorshipRepository;

    @Override
    public Optional<SongAuthorship> getById(int id) {
        return songAuthorshipRepository.findById(id);
    }
    @Override
    public Iterable<SongAuthorship> getAll() {
        return songAuthorshipRepository.findAll();
    }
    @Override
    public SongAuthorship save(SongAuthorship songAuthorship) {
        return songAuthorshipRepository.save(songAuthorship);
    }
    @Override
    public void delete(int id) {
        songAuthorshipRepository.deleteById(id);
    }

    @Override
    public Iterable<SongAuthorship> getBySongId(int id) { return songAuthorshipRepository.findBySongId(id); }
    @Override
    public Iterable<SongAuthorship> getByAuthorId(int id) { return songAuthorshipRepository.findByAuthorId(id); }
}

