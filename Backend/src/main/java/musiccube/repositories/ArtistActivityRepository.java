package musiccube.repositories;

import musiccube.entities.ArtistActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistActivityRepository extends CrudRepository<ArtistActivity,Integer> {
    @Query("SELECT a FROM ArtistActivity a WHERE a.artist.id=?1")
    Iterable<ArtistActivity> findByArtistId(int artistId);
    @Query("SELECT a FROM ArtistActivity a WHERE a.band.id=?1")
    Iterable<ArtistActivity> findByBandId(int bandId);
    @Query("SELECT a FROM ArtistActivity a WHERE a.artist.id=?1 AND a.isActive=?2")
    Iterable<ArtistActivity> findByArtistIdIsActive(int artistId, boolean active);
    @Query("SELECT a FROM ArtistActivity a WHERE a.band.id=?1 AND a.isActive=?2")
    Iterable<ArtistActivity> findByBandIdIsActive(int bandId, boolean active);

}
