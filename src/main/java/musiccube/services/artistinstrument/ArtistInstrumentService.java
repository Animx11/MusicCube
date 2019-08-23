package musiccube.services.artistinstrument;

import musiccube.entities.ArtistInstrument;

import java.util.Optional;

public interface ArtistInstrumentService {

    Optional<ArtistInstrument> getById(int id);
    Iterable<ArtistInstrument> getAll();
    ArtistInstrument save(ArtistInstrument artistInstrument);
    void delete(int id);

    Iterable<ArtistInstrument> getByArtistId(int artistId);
    Iterable<ArtistInstrument> getByInstrumentId(int instrumentId);
    boolean existsByArtistIdAndInstrumentId(int artistId, int instrumentId);

}