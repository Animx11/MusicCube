package musiccube.repositories;

import musiccube.dtos.SongRatingDto;
import musiccube.entities.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RateRepository extends CrudRepository<Rate, Integer>, PagingAndSortingRepository<Rate, Integer> {

    @Query("SELECT r FROM Rate r where r.user.userName like ?1 AND r.song.id = ?2")
    Optional<Rate> findRateByUserNameAndSongId(String userName, int id);

    @Query("SELECT r FROM Rate r where r.user.userName like ?1 AND r.album.id = ?2")
    Optional<Rate> findRateByUserNameAndAlbumId(String userName, int id);

    @Query("SELECT r FROM Rate r where r.user.userName like ?1 AND r.band.id = ?2")
    Optional<Rate> findRateByUserNameAndBandId(String userName, int id);

    @Query("SELECT r FROM Rate r where r.user.userName like ?1 AND r.artist.id = ?2")
    Optional<Rate> findRateByUserNameAndArtistId(String userName, int id);



    @Query("SELECT r FROM Rate r WHERE r.user.userName like ?1")
    Iterable<Rate> findAllUserRates(String userName);

    @Query("SELECT r FROM Rate r WHERE r.artist.id = ?1")
    Iterable<Rate> findAllArtistRatesByArtistId(int id);

    @Query("SELECT r FROM Rate r WHERE r.album.id = ?1")
    Iterable<Rate> findAllAlbumRatesByAlbumId(int id);

    @Query("SELECT r FROM Rate r WHERE r.song.id = ?1")
    Iterable<Rate> findAllSongRatesBySongId(int id);

    @Query("SELECT r FROM Rate r WHERE r.band.id = ?1")
    Iterable<Rate> findAllBandRatesByBandId(int id);


    @Query("SELECT r.song.id, AVG(r.rate), COUNT(r) FROM Rate r GROUP BY r.song")
    Iterable<SongRatingDto> findSongStatistics();

}
