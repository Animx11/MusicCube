package musiccube.services.rate;

import musiccube.entities.Rate;

import java.util.Optional;

public interface RateService {

    Optional<Rate> getById(int id);
    Iterable<Rate> getAll();
    Rate save(Rate rate);
    void delete(int id);

    Optional<Rate> getRateByUserNameAndSongId(String userName, int id);
    Iterable<Rate> getAllUserRates(String userName);

}
