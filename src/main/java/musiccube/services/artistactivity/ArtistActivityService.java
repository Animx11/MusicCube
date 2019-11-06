package musiccube.services.artistactivity;

import musiccube.entities.ArtistActivity;

import java.util.Optional;

public interface ArtistActivityService {

    Optional<ArtistActivity> getById(int id);
    Iterable<ArtistActivity> getAll();
    ArtistActivity save(ArtistActivity artistActivity);
    void delete(int id);

    Iterable<ArtistActivity> getByArtistId(int artistId);
    Iterable<ArtistActivity> getByBandId(int bandId);
    Iterable<ArtistActivity> getByArtistIdIsActive(int artistId, boolean active);
    Iterable<ArtistActivity> getByBandIdIsActive(int bandId, boolean active);

}
