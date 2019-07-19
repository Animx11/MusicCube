package MusicCube.repositories;

import MusicCube.entities.Instrument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InstrumentRepository extends CrudRepository<Instrument,Integer>, PagingAndSortingRepository<Instrument,Integer> {

    @Query("SELECT i FROM Instrument i WHERE LOWER(i.instrumentName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Instrument> findByInstrumentName(String instrumentName);

    boolean existsByInstrumentName(String instrumentName);
}