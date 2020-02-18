package musiccube.repositories;

import musiccube.entities.SongInstrument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SongInstrumentRepository extends CrudRepository<SongInstrument,Integer> {
    @Query("select s from SongInstrument s where s.song.id = ?1")
    Iterable<SongInstrument> findBySongId(int id);
    @Query("select s from SongInstrument s where s.instrument.id = ?1")
    Iterable<SongInstrument> findByInstrumentId(int id);
}
