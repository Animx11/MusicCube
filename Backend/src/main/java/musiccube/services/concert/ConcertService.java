package musiccube.services.concert;

import musiccube.entities.Concert;
import java.util.Optional;

public interface ConcertService {

    Optional<Concert> getById(int id);
    Iterable<Concert> getAll();
    Concert save(Concert concert);
    void delete(int id);

    Iterable<Concert> getByConcertName(String concertName);
}
