package MusicCube.services.artistinband;

import MusicCube.entities.Artist;
import MusicCube.entities.ArtistInBand;
import MusicCube.entities.Band;

import java.util.Optional;

public interface ArtistInBandService {

    Optional<ArtistInBand> getById(int id);
    Iterable<ArtistInBand> getAll();
    ArtistInBand save(ArtistInBand artistInBand);
    void delete(int id);

    Iterable<ArtistInBand> getByArtist(Artist artist);
    Iterable<ArtistInBand> getByBand(Band band);

}
