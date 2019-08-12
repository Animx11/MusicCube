package musiccube.repositories;

import musiccube.entities.Artist;
import musiccube.entities.ArtistInstrument;
import musiccube.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface ArtistInstrumentRepository extends CrudRepository<ArtistInstrument,Integer> {
    Iterable<ArtistInstrument> findByArtist(Artist artist);
    Iterable<ArtistInstrument> findByInstrument(Instrument instrument);
}
