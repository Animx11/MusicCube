package musiccube.services.artistinstrument;

import musiccube.entities.Artist;
import musiccube.entities.ArtistInstrument;
import musiccube.entities.Instrument;
import musiccube.repositories.ArtistInstrumentRepository;
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

    @Override
    public Iterable<ArtistInstrument> getByArtist(Artist artist) { return artistInstrumentRepository.findByArtist(artist); }
    @Override
    public Iterable<ArtistInstrument> getByInstrument(Instrument instrument) { return artistInstrumentRepository.findByInstrument(instrument); }
}
