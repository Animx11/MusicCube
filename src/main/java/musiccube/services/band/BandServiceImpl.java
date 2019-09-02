package musiccube.services.band;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Genre;
import musiccube.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public Optional<Band> getById(int id) {
        return bandRepository.findById(id);
    }
    @Override
    public Iterable<Band> getAll() {
        return bandRepository.findAll();
    }

    @Override
    public Iterable<Band> getAllPaging(Integer pageNr, Integer perPage) {
        return bandRepository.findAll(new PageRequest(pageNr,perPage));
    }

    @Override
    public Band save(Band band) {
        return bandRepository.save(band);
    }
    @Override
    public void delete(int id) {
        bandRepository.deleteById(id);
    }

    @Override
    public Iterable<Band> getByBandName(String bandName) {
        return bandRepository.findByBandName(bandName);
    }

    @Override
    public Iterable<Band> getByCity(String cityName) {
        return bandRepository.findByCity(cityName);
    }


    @Override
    public Iterable<Genre> getBandGenres(int bandId) {
        return bandRepository.findBandGenres(bandId);
    }

    @Override
    public Iterable<Album> getBandAlbums(int bandId) {
        return bandRepository.findBandAlbums(bandId);
    }
    @Override
    public boolean existsByBandName(String bandName){
        return bandRepository.existsByBandName(bandName);
    }
    @Override
    public boolean existsByMbId(String mbid) {
        return bandRepository.existsByMbId(mbid);
    }
}
