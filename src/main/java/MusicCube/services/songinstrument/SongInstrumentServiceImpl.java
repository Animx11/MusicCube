package MusicCube.services.songinstrument;

import MusicCube.entities.Song;
import MusicCube.entities.SongInstrument;
import MusicCube.entities.Instrument;
import MusicCube.repositories.SongInstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongInstrumentServiceImpl implements SongInstrumentService {

    @Autowired
    private SongInstrumentRepository songInstrumentRepository;

    @Override
    public Optional<SongInstrument> getById(int id) {
        return songInstrumentRepository.findById(id);
    }
    @Override
    public Iterable<SongInstrument> getAll() {
        return songInstrumentRepository.findAll();
    }
    @Override
    public SongInstrument save(SongInstrument songInstrument) {
        return songInstrumentRepository.save(songInstrument);
    }
    @Override
    public void delete(int id) {
        songInstrumentRepository.deleteById(id);
    }

    @Override
    public Iterable<SongInstrument> getBySong(Song song) { return songInstrumentRepository.findBySong(song); }
    @Override
    public Iterable<SongInstrument> getByInstrument(Instrument instrument) { return songInstrumentRepository.findByInstrument(instrument); }
}
