package MusicCube.services.artistinband;

import MusicCube.entities.Artist;
import MusicCube.entities.ArtistInBand;
import MusicCube.entities.Band;
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

    @Override
    public Iterable<ArtistInBand> getByArtistId(int artistId) {
        return artistInBandRepository.findByArtistId(artistId);
    }
    @Override
    public Iterable<ArtistInBand> getByBandId(int bandId) {
        return artistInBandRepository.findByBandId(bandId);
    }

    @Override
    public Iterable<ArtistInBand> getByArtistIdIsActive(int artistId, boolean active) {
        return artistInBandRepository.findByArtistIdIsActive(artistId,active);
    }

    @Override
    public Iterable<ArtistInBand> getByBandIdIsActive(int bandId, boolean active) {
        return artistInBandRepository.findByBandIdIsActive(bandId,active);
    }
}
