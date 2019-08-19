package musiccube.services.bandconcert;

import musiccube.entities.Band;
import musiccube.entities.BandConcert;
import musiccube.entities.Concert;

import java.util.Optional;

public interface BandConcertService {

    Optional<BandConcert> getById(int id);
    Iterable<BandConcert> getAll();
    BandConcert save(BandConcert BandConcert);
    void delete(int id);

    Iterable<BandConcert> getByBand(Band band);
    Iterable<BandConcert> getByConcert(Concert concert);
}
