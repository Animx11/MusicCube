package MusicCube.services.artist;

import MusicCube.entities.Artist;
import MusicCube.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean existsArtistByArtistFirstNamesAndLastName(String firstNames, String lastNames){
        return artistRepository.existsByFirstNamesAndLastName(firstNames, lastNames);
    }
    @Override
    public boolean existsArtistByStageName(String stageName){
        return artistRepository.existsArtistByStageName(stageName);
    }

}
