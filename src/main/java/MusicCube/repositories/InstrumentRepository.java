package MusicCube.Repositories;

import MusicCube.Entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentsRepository extends CrudRepository<Instrument,Integer> {
}