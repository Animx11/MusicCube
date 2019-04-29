package MusicCube.services.bandconcert;

import MusicCube.entities.Band;
import MusicCube.entities.BandConcert;
import MusicCube.entities.Concert;
import MusicCube.repositories.BandConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BandConcertServiceImpl implements BandConcertService {

    @Autowired
    private BandConcertRepository bandConcertRepository;

    @Override
    public Optional<BandConcert> getById(int id) {
        return bandConcertRepository.findById(id);
    }
    @Override
    public Iterable<BandConcert> getAll() {
        return bandConcertRepository.findAll();
    }
    @Override
    public BandConcert save(BandConcert bandConcert) {
        return bandConcertRepository.save(bandConcert);
    }
    @Override
    public void delete(int id) {
        bandConcertRepository.deleteById(id);
    }

    @Override
    public Iterable<BandConcert> getByBand(Band band) { return bandConcertRepository.findByBand(band); }
    @Override
    public Iterable<BandConcert> getByConcert(Concert concert) { return bandConcertRepository.findByConcert(concert); }
}
