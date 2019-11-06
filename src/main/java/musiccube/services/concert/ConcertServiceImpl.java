package musiccube.services.concert;

import musiccube.entities.Concert;
import musiccube.repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcertServiceImpl implements ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    @Override
    public Optional<Concert> getById(int id) {
        return concertRepository.findById(id);
    }
    @Override
    public Iterable<Concert> getAll() {
        return concertRepository.findAll();
    }
    @Override
    public Concert save(Concert concert) {
        return concertRepository.save(concert);
    }
    @Override
    public void delete(int id) {
        concertRepository.deleteById(id);
    }

    @Override
    public Iterable<Concert> getByConcertName(String concertName) {
        return concertRepository.findByConcertName(concertName);
    }

    @Override
    public Iterable<Concert> getByCityName(String cityName) {
        return concertRepository.findByCityName(cityName);
    }
}
