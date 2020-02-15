package musiccube.repositories;

import musiccube.dtos.RankingDto;
import musiccube.dtos.SongRatingDto;
import musiccube.entities.Rate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
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


    @Query(
            value = "SELECT new musiccube.dtos.SongRatingDto( r.song, AVG(r.rate) AS avgrate, COUNT(r)) " +
                "FROM Rate r GROUP BY r.song " +
                "ORDER BY avgrate DESC"
    )
    List<SongRatingDto> findBestRatedSongs(Pageable pageable);
    @Query(
            value = "SELECT new musiccube.dtos.RankingDto( r.song, AVG(r.rate) AS avgrate, COUNT(r)) " +
                    "FROM Rate r GROUP BY r.song " +
                    "ORDER BY avgrate DESC"
    )
    List<RankingDto> findBestRatedSongsUpdated(Pageable pageable);
    @Query(
            value = "SELECT new musiccube.dtos.RankingDto( r.band, AVG(r.rate) AS avgrate, COUNT(r)) " +
                    "FROM Rate r GROUP BY r.band " +
                    "ORDER BY avgrate DESC"
    )
    List<RankingDto> findBestRatedBands(Pageable pageable);
    @Query(
            value = "SELECT new musiccube.dtos.RankingDto( r.album, AVG(r.rate) AS avgrate, COUNT(r)) " +
                    "FROM Rate r GROUP BY r.album " +
                    "ORDER BY avgrate DESC"
    )
    List<RankingDto> findBestRatedAlbums(Pageable pageable);
    @Query(
            value = "SELECT new musiccube.dtos.RankingDto( r.artist, AVG(r.rate) AS avgrate, COUNT(r)) " +
                    "FROM Rate r GROUP BY r.artist " +
                    "ORDER BY avgrate DESC"
    )
    List<RankingDto> findBestRatedArtists(Pageable pageable);
    

}
