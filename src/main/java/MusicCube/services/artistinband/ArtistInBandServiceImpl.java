package MusicCube.services.artistinband;

import MusicCube.entities.ArtistInBand;
import MusicCube.repositories.ArtistInBandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistInBandServiceImpl implements ArtistInBandService {

    @Autowired
    private ArtistInBandRepository artistInBandRepository;

    @Override
    public Optional<ArtistInBand> getById(int id) {
        return artistInBandRepository.findById(id);
    }
    @Override
    public Iterable<ArtistInBand> getAll() {
        return artistInBandRepository.findAll();
    }
    @Override
    public ArtistInBand save(ArtistInBand artistInBand) {
        return artistInBandRepository.save(artistInBand);
    }
    @Override
    public void delete(int id) {
        artistInBandRepository.deleteById(id);
    }
}
