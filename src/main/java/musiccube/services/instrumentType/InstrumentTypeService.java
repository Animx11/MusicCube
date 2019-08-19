package musiccube.services.instrumentType;

import musiccube.entities.InstrumentType;

import java.util.Optional;

public interface InstrumentTypeService {

    Optional<InstrumentType> getById(int id);
    Iterable<InstrumentType> getAll();
    Iterable<InstrumentType> getAllPaging(Integer pageNr, Integer perPage);
    InstrumentType save(InstrumentType instrumentType);
    void delete(int id);

    Iterable<InstrumentType> getByInstrumentTypeName(String instrumentTypeName);

}
