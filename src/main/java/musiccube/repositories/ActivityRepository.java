package musiccube.repositories;

import musiccube.entities.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity,Integer> {
    @Query("SELECT a FROM Activity a WHERE a.artist.id=?1")
    Iterable<Activity> findByArtistId(int artistId);
    @Query("SELECT a FROM Activity a WHERE a.band.id=?1")
    Iterable<Activity> findByBandId(int bandId);
    @Query("SELECT a FROM Activity a WHERE a.artist.id=?1 AND a.isActive=?2")
    Iterable<Activity> findByArtistIdIsActive(int artistId, boolean active);
    @Query("SELECT a FROM Activity a WHERE a.band.id=?1 AND a.isActive=?2")
    Iterable<Activity> findByBandIdIsActive(int bandId, boolean active);

}
