package MusicCube.repositories;

import MusicCube.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument,Integer> {
}