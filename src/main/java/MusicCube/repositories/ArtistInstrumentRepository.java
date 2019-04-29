package MusicCube.repositories;

import MusicCube.entities.Artist;
import MusicCube.entities.ArtistInstrument;
import MusicCube.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface ArtistInstrumentRepository extends CrudRepository<ArtistInstrument,Integer> {
    Iterable<ArtistInstrument> findByArtist(Artist artist);
    Iterable<ArtistInstrument> findByInstrument(Instrument instrument);
}
