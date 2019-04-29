package MusicCube.services.bandconcert;

import MusicCube.entities.Band;
import MusicCube.entities.BandConcert;
import MusicCube.entities.Concert;

import java.util.Optional;

public interface BandConcertService {

    Optional<BandConcert> getById(int id);
    Iterable<BandConcert> getAll();
    BandConcert save(BandConcert BandConcert);
    void delete(int id);

    Iterable<BandConcert> getByBand(Band band);
    Iterable<BandConcert> getByConcert(Concert concert);
}
