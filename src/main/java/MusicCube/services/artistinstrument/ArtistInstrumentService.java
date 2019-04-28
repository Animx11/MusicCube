package MusicCube.services.artistinstrument;

import MusicCube.entities.ArtistInstrument;

import java.util.Optional;

public interface ArtistInstrumentService {

    Optional<ArtistInstrument> getById(int id);
    Iterable<ArtistInstrument> getAll();
    ArtistInstrument save(ArtistInstrument artistInstrument);
    void delete(int id);

}