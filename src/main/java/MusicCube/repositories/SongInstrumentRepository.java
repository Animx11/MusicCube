package MusicCube.repositories;

import MusicCube.entities.Song;
import MusicCube.entities.SongInstrument;
import MusicCube.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface SongInstrumentRepository extends CrudRepository<SongInstrument,Integer> {
    Iterable<SongInstrument> findBySong(Song song);
    Iterable<SongInstrument> findByInstrument(Instrument instrument);
}
