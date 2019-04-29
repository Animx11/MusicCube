package MusicCube.services.subgenre;

import MusicCube.entities.Genre;
import MusicCube.entities.Subgenre;
import MusicCube.repositories.SubgenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubgenreServiceImpl implements SubgenreService {

    @Autowired
    private SubgenreRepository subgenreRepository;

    @Override
    public Optional<Subgenre> getById(int id) {
        return subgenreRepository.findById(id);
    }
    @Override
    public Iterable<Subgenre> getAll() {
        return subgenreRepository.findAll();
    }
    @Override
    public Subgenre save(Subgenre subgenre) {
        return subgenreRepository.save(subgenre);
    }
    @Override
    public void delete(int id) {
        subgenreRepository.deleteById(id);
    }

    @Override
    public Iterable<Subgenre> getByChildGenre(Genre childGenre) { return subgenreRepository.findByChildGenre(childGenre); }
    @Override
    public Iterable<Subgenre> getByParentGenre(Genre parentGenre) { return subgenreRepository.findByParentGenre(parentGenre); }
}
