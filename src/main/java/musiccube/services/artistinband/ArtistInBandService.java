package musiccube.services.artistinband;

import musiccube.entities.ArtistInBand;

import java.util.Optional;

public interface ArtistInBandService {

    Optional<ArtistInBand> getById(int id);
    Iterable<ArtistInBand> getAll();
    ArtistInBand save(ArtistInBand artistInBand);
    void delete(int id);

    Iterable<ArtistInBand> getByArtistId(int artistId);
    Iterable<ArtistInBand> getByBandId(int bandId);
    Iterable<ArtistInBand> getByArtistIdIsActive(int artistId, boolean active);
    Iterable<ArtistInBand> getByBandIdIsActive(int bandId, boolean active);

}
