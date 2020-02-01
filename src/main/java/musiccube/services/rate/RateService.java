package musiccube.services.rate;

import musiccube.dtos.RatingDto;
import musiccube.dtos.SongRatingDto;
import musiccube.entities.Rate;

import java.util.List;
import java.util.Optional;

public interface RateService {

    Optional<Rate> getById(int id);
    Iterable<Rate> getAll();
    Rate save(Rate rate);
    void delete(int id);

    Optional<Rate> getRateByUserNameAndSongId(String userName, int id);
    Optional<Rate> getRateByUserNameAndAlbumId(String userName, int id);
    Optional<Rate> getRateByUserNameAndBandId(String userName, int id);
    Optional<Rate> getRateByUserNameAndArtistId(String userName, int id);


    Iterable<Rate> getAllUserRates(String userName);
    Iterable<Rate> getRatesByArtistId(int id);
    Iterable<Rate> getRatesBySongId(int id);
    Iterable<Rate> getRatesByBandId(int id);
    Iterable<Rate> getRatesByAlbumId(int id);

    List<SongRatingDto> getBestRatedSongs(int limit);

    List<RatingDto> getBestRated(String tableName, int size);
}
