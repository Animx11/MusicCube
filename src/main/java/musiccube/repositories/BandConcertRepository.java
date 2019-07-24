package musiccube.repositories;

import musiccube.entities.Band;
import musiccube.entities.BandConcert;
import musiccube.entities.Concert;
import org.springframework.data.repository.CrudRepository;

public interface BandConcertRepository extends CrudRepository<BandConcert,Integer> {
    Iterable<BandConcert> findByBand(Band band);
    Iterable<BandConcert> findByConcert(Concert concert);
}
