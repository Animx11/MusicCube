package MusicCube.services.artistinstrument;

import MusicCube.entities.Artist;
import MusicCube.entities.ArtistInstrument;
import MusicCube.entities.Instrument;

import java.util.Optional;

public interface ArtistInstrumentService {

    Optional<ArtistInstrument> getById(int id);
    Iterable<ArtistInstrument> getAll();
    ArtistInstrument save(ArtistInstrument artistInstrument);
    void delete(int id);

    Iterable<ArtistInstrument> getByArtist(Artist artist);
    Iterable<ArtistInstrument> getByInstrument(Instrument instrument);

}