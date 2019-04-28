package MusicCube.services.bandconcert;

import MusicCube.entities.BandConcert;
import java.util.Optional;

public interface BandConcertService {

    Optional<BandConcert> getById(int id);

    Iterable<BandConcert> getAll();

    BandConcert save(BandConcert BandConcert);

    void delete(int id);
}
