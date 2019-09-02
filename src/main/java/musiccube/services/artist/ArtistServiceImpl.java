package musiccube.services.artist;

import musiccube.entities.Artist;
import musiccube.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Optional<Artist> getById(int id) {
        return artistRepository.findById(id);
    }
    @Override
    public Iterable<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public Iterable<Artist> getAllPaging(Integer pageNr, Integer perPage) {
        return artistRepository.findAll(new PageRequest(pageNr,perPage));
    }

    @Override
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }
    @Override
    public void delete(int id) {
        artistRepository.deleteById(id);
    }

    @Override
    public Iterable<Artist> getByStageName(String stageName) {
        return artistRepository.findByStageName(stageName);
    }

    @Override
    public Iterable<Artist> getByCity(String cityName) {
        return artistRepository.findByCity(cityName);
    }


    @Override
    public Iterable<Artist> getByAnything(String input) {
        return artistRepository.findByAnything(input);
    }

    @Override
    public Artist getByMbId(String mbId) {
        return artistRepository.findByMbId(mbId);
    }

    @Override
    public boolean existsByMbId(String mbId) {
        return artistRepository.existsByMbId(mbId);
    }
}
