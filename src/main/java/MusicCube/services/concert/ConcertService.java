package MusicCube.services.concert;

import MusicCube.entities.Concert;
import java.util.Optional;

public interface ConcertService {

    Optional<Concert> getById(int id);

    Iterable<Concert> getAll();

    Concert save(Concert concert);

    void delete(int id);

    Iterable<Concert> getByName(String name);
}
