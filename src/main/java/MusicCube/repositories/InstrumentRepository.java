package MusicCube.repositories;

import MusicCube.entities.Instrument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument,Integer> {

    @Query("SELECT i FROM Instrument i WHERE i.instrumentName LIKE CONCAT('%',?1,'%')")
    Iterable<Instrument> findByInstrumentName(String instrumentName);
}