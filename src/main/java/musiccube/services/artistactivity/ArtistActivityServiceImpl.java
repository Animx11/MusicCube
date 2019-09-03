package musiccube.services.artistactivity;

import musiccube.entities.Activity;
import musiccube.repositories.ArtistActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistActivityServiceImpl implements ArtistActivityService {

    @Autowired
    private ArtistActivityRepository artistActivityRepository;

    @Override
    public Optional<Activity> getById(int id) {
        return artistActivityRepository.findById(id);
    }
    @Override
    public Iterable<Activity> getAll() {
        return artistActivityRepository.findAll();
    }
    @Override
    public Activity save(Activity activity) {
        return artistActivityRepository.save(activity);
    }
    @Override
    public void delete(int id) {
        artistActivityRepository.deleteById(id);
    }

    @Override
    public Iterable<Activity> getByArtistId(int artistId) {
        return artistActivityRepository.findByArtistId(artistId);
    }
    @Override
    public Iterable<Activity> getByBandId(int bandId) {
        return artistActivityRepository.findByBandId(bandId);
    }

    @Override
    public Iterable<Activity> getByArtistIdIsActive(int artistId, boolean active) {
        return artistActivityRepository.findByArtistIdIsActive(artistId,active);
    }

    @Override
    public Iterable<Activity> getByBandIdIsActive(int bandId, boolean active) {
        return artistActivityRepository.findByBandIdIsActive(bandId,active);
    }
}
