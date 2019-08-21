package musiccube.services.artistinstrument;

import musiccube.entities.Artist;
import musiccube.entities.ArtistInstrument;
import musiccube.entities.Instrument;

import java.util.Optional;

public interface ArtistInstrumentService {

    Optional<ArtistInstrument> getById(int id);
    Iterable<ArtistInstrument> getAll();
    ArtistInstrument save(ArtistInstrument artistInstrument);
    void delete(int id);

    Iterable<ArtistInstrument> getByArtist(Artist artist);
    Iterable<ArtistInstrument> getByInstrument(Instrument instrument);
    boolean existsByArtistIdAndInstrumentId(int artistId, int instrumentId);

}