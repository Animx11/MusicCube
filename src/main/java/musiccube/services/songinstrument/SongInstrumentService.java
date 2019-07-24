package musiccube.services.songinstrument;

import musiccube.entities.SongInstrument;

import java.util.Optional;

public interface SongInstrumentService {

    Optional<SongInstrument> getById(int id);
    Iterable<SongInstrument> getAll();
    SongInstrument save(SongInstrument songInstrument);
    void delete(int id);

    Iterable<SongInstrument> getBySongId(int id);
    Iterable<SongInstrument> getByInstrumentId(int id);

}