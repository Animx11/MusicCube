package MusicCube.services.artistinstrument;

import MusicCube.entities.ArtistInstrument;
import MusicCube.repositories.ArtistInstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistInstrumentServiceImpl implements ArtistInstrumentService {

    @Autowired
    private ArtistInstrumentRepository artistInstrumentRepository;

    @Override
    public Optional<ArtistInstrument> getById(int id) {
        return artistInstrumentRepository.findById(id);
    }
    @Override
    public Iterable<ArtistInstrument> getAll() {
        return artistInstrumentRepository.findAll();
    }
    @Override
    public ArtistInstrument save(ArtistInstrument artistInstrument) {
        return artistInstrumentRepository.save(artistInstrument);
    }
    @Override
    public void delete(int id) {
        artistInstrumentRepository.deleteById(id);
    }
}
