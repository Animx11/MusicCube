package musiccube.services.rate;

import musiccube.entities.Rate;
import musiccube.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

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
    public Iterable<Rate> getAllUserRates(String userName) {
        return rateRepository.findAllUserRates(userName);
    }

}
