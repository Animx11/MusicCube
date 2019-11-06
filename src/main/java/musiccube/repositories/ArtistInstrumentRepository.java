package musiccube.repositories;

import musiccube.entities.ArtistInstrument;
import org.springframework.data.repository.CrudRepository;

public interface ArtistInstrumentRepository extends CrudRepository<ArtistInstrument,Integer> {
    Iterable<ArtistInstrument> findByArtistId(int artistId);
    Iterable<ArtistInstrument> findByInstrumentId(int instrumentId);
    boolean existsByArtistIdAndInstrumentId(int artistId, int instrumentId);
}
