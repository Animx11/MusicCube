package musiccube.services.artistactivity;

import musiccube.entities.ArtistActivity;
import musiccube.repositories.ArtistActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistActivityServiceImpl implements ArtistActivityService {

    @Autowired
    private ArtistActivityRepository artistActivityRepository;

    @Override
    public Optional<ArtistActivity> getById(int id) {
        return artistActivityRepository.findById(id);
    }
    @Override
    public Iterable<ArtistActivity> getAll() {
        return artistActivityRepository.findAll();
    }
    @Override
    public ArtistActivity save(ArtistActivity artistActivity) {
        return artistActivityRepository.save(artistActivity);
    }
    @Override
    public void delete(int id) {
        artistActivityRepository.deleteById(id);
    }

    @Override
    public Iterable<ArtistActivity> getByArtistId(int artistId) {
        return artistActivityRepository.findByArtistId(artistId);
    }
    @Override
    public Iterable<ArtistActivity> getByBandId(int bandId) {
        return artistActivityRepository.findByBandId(bandId);
    }

    @Override
    public Iterable<ArtistActivity> getByArtistIdIsActive(int artistId, boolean active) {
        return artistActivityRepository.findByArtistIdIsActive(artistId,active);
    }

    @Override
    public Iterable<ArtistActivity> getByBandIdIsActive(int bandId, boolean active) {
        return artistActivityRepository.findByBandIdIsActive(bandId,active);
    }
}
