package musiccube.services.instrumentType;

import musiccube.entities.InstrumentType;
import musiccube.repositories.InstrumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstrumentTypeServiceImpl implements InstrumentTypeService {

    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;

    @Override
    public Optional<InstrumentType> getById(int id) {
        return instrumentTypeRepository.findById(id);
    }

    @Override
    public Iterable<InstrumentType> getAll() {
        return instrumentTypeRepository.findAll();
    }

    @Override
    public Iterable<InstrumentType> getAllPaging(Integer pageNr, Integer perPage) {
        return instrumentTypeRepository.findAll(new PageRequest(pageNr, perPage));
    }

    @Override
    public InstrumentType save(InstrumentType instrumentType) {
        return instrumentTypeRepository.save(instrumentType);
    }

    @Override
    public void delete(int id) {
        instrumentTypeRepository.deleteById(id);
    }

    @Override
    public Iterable<InstrumentType> getByInstrumentTypeName(String instrumentTypeName) {
        return instrumentTypeRepository.findByInstrumentTypeName(instrumentTypeName);
    }

}
