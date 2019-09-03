package musiccube.repositories;

import musiccube.entities.Band;
import musiccube.entities.BandConcert;
import musiccube.entities.Concert;
import org.springframework.data.repository.CrudRepository;

public interface BandConcertRepository extends CrudRepository<BandConcert,Integer> {
    Iterable<BandConcert> findByBandId(int bandId);
    Iterable<BandConcert> findByConcertId(int concertId);
}
