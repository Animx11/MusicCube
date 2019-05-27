package MusicCube.services.instrument;

import MusicCube.entities.Instrument;

import java.util.Optional;

public interface InstrumentService {

    Optional<Instrument> getById(int id);
    Iterable<Instrument> getAll();
    Instrument save(Instrument instrument);
    void delete(int id);

    Iterable<Instrument> getByInstrumentName(String instrumentName);
    boolean existsByInstrumentName(String instrumentName);

}
