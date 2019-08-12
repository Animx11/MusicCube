package musiccube.repositories;

import musiccube.entities.ArtistInBand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistInBandRepository extends CrudRepository<ArtistInBand,Integer> {
    @Query("SELECT a FROM ArtistInBand a WHERE a.artist.id=?1")
    Iterable<ArtistInBand> findByArtistId(int artistId);
    @Query("SELECT a FROM ArtistInBand a WHERE a.band.id=?1")
    Iterable<ArtistInBand> findByBandId(int bandId);
    @Query("SELECT a FROM ArtistInBand a WHERE a.artist.id=?1 AND a.isActive=?2")
    Iterable<ArtistInBand> findByArtistIdIsActive(int artistId, boolean active);
    @Query("SELECT a FROM ArtistInBand a WHERE a.band.id=?1 AND a.isActive=?2")
    Iterable<ArtistInBand> findByBandIdIsActive(int bandId, boolean active);

}
