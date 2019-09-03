package musiccube.services.Activity;

import musiccube.entities.Activity;
import musiccube.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository ActivityRepository;

    @Override
    public Optional<Activity> getById(int id) {
        return ActivityRepository.findById(id);
    }
    @Override
    public Iterable<Activity> getAll() {
        return ActivityRepository.findAll();
    }
    @Override
    public Activity save(Activity activity) {
        return ActivityRepository.save(activity);
    }
    @Override
    public void delete(int id) {
        ActivityRepository.deleteById(id);
    }

    @Override
    public Iterable<Activity> getByArtistId(int artistId) {
        return ActivityRepository.findByArtistId(artistId);
    }
    @Override
    public Iterable<Activity> getByBandId(int bandId) {
        return ActivityRepository.findByBandId(bandId);
    }

    @Override
    public Iterable<Activity> getByArtistIdIsActive(int artistId, boolean active) {
        return ActivityRepository.findByArtistIdIsActive(artistId,active);
    }

    @Override
    public Iterable<Activity> getByBandIdIsActive(int bandId, boolean active) {
        return ActivityRepository.findByBandIdIsActive(bandId,active);
    }
}
