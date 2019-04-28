package MusicCube.services.genreinstrument;

import MusicCube.entities.GenreInstrument;
import MusicCube.repositories.GenreInstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreInstrumentServiceImpl implements GenreInstrumentService {

    @Autowired
    private GenreInstrumentRepository genreInstrumentRepository;

    @Override
    public Optional<GenreInstrument> getById(int id) {
        return genreInstrumentRepository.findById(id);
    }
    @Override
    public Iterable<GenreInstrument> getAll() {
        return genreInstrumentRepository.findAll();
    }
    @Override
    public GenreInstrument save(GenreInstrument genreInstrument) {
        return genreInstrumentRepository.save(genreInstrument);
    }
    @Override
    public void delete(int id) {
        genreInstrumentRepository.deleteById(id);
    }
}
