package MusicCube.repositories;

import MusicCube.entities.Band;
import MusicCube.entities.BandConcert;
import MusicCube.entities.Concert;
import org.springframework.data.repository.CrudRepository;

public interface BandConcertRepository extends CrudRepository<BandConcert,Integer> {
    Iterable<BandConcert> findByBand(Band band);
    Iterable<BandConcert> findByConcert(Concert concert);
}
