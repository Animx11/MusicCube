package musiccube.services.rate;

import musiccube.dtos.RatingDto;
import musiccube.dtos.SongRatingDto;
import musiccube.entities.Rate;
import musiccube.ratings.RatingDtoFactory;
import musiccube.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;
    private RatingDtoFactory dtoFactory = new RatingDtoFactory();

    @Override
    public Optional<Rate> getById(int id) {
        return rateRepository.findById(id);
    }

    @Override
    public Iterable<Rate> getAll() {
        return rateRepository.findAll();
    }

    @Override
    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public void delete(int id) {
        rateRepository.deleteById(id);
    }

    @Override
    public Optional<Rate> getRateByUserNameAndSongId(String userName, int id) {
        return rateRepository.findRateByUserNameAndSongId(userName, id);
    }

    @Override
    public Optional<Rate> getRateByUserNameAndAlbumId(String userName, int id) {
        return rateRepository.findRateByUserNameAndAlbumId(userName, id);
    }

    @Override
    public Optional<Rate> getRateByUserNameAndBandId(String userName, int id) {
        return rateRepository.findRateByUserNameAndBandId(userName, id);
    }

    @Override
    public Optional<Rate> getRateByUserNameAndArtistId(String userName, int id) {
        return rateRepository.findRateByUserNameAndArtistId(userName, id);
    }



    @Override
    public Iterable<Rate> getAllUserRates(String userName) {
        return rateRepository.findAllUserRates(userName);
    }

    @Override
    public Iterable<Rate> getRatesByArtistId(int id) {
        return rateRepository.findAllArtistRatesByArtistId(id);
    }

    @Override
    public Iterable<Rate> getRatesByAlbumId(int id) {
        return rateRepository.findAllAlbumRatesByAlbumId(id);
    }

    @Override
    public Iterable<Rate> getRatesByBandId(int id) {
        return rateRepository.findAllBandRatesByBandId(id);
    }

    @Override
    public Iterable<Rate> getRatesBySongId(int id) {
        return rateRepository.findAllSongRatesBySongId(id);
    }



    @Override
    public List<SongRatingDto> getBestRatedSongs(int limit) {
        return rateRepository.findBestRatedSongs(new PageRequest(0, limit));
    }

    @Override
    public List<RatingDto> getBestRated(String tableName, int size) {
        return dtoFactory.getRatingDtos(tableName,size);
    }
}
