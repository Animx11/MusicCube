package musiccube.services.instrument;

import musiccube.entities.Instrument;
import musiccube.repositories.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Override
    public Optional<Instrument> getById(int id) {
        return instrumentRepository.findById(id);
    }

    @Override
    public Iterable<Instrument> getAll() {
        return instrumentRepository.findAll();
    }

    @Override
    public Iterable<Instrument> getAllPaging(Integer pageNr, Integer perPage) {
        return instrumentRepository.findAll(PageRequest.of(pageNr,perPage));
    }

    @Override
    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Override
    public void delete(int id) {
        instrumentRepository.deleteById(id);
    }

    @Override
    public Iterable<Instrument> getByInstrumentName(String instrumentName) { return instrumentRepository.findByInstrumentName(instrumentName); }

    @Override
    public Iterable<Instrument> getByInstrumentType(String instrumentTypeName) { return instrumentRepository.findByInstrumentType(instrumentTypeName); }


    @Override
    public boolean existsByInstrumentName(String instrumentName){
        return instrumentRepository.existsByInstrumentName(instrumentName);
    }

    @Override
    public Instrument getOneByName(String name) {
        return instrumentRepository.findOneByInstrumentName(name);
    }
}