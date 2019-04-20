package MusicCube.services.artistinband;

import MusicCube.entities.ArtistInBand;

import java.util.Optional;

public interface ArtistInBandService {

    Optional<ArtistInBand> getById(int id);
    Iterable<ArtistInBand> getAll();
    ArtistInBand save(ArtistInBand artistInBand);
    void delete(int id);

}
