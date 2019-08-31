package musiccube.services.artist;

import musiccube.entities.Artist;
import musiccube.entities.ArtistActivity;
import musiccube.repositories.ArtistActivityRepository;
import musiccube.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    ArtistActivityRepository activityRepository;

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

    @Override
    public Iterable<Artist> advancedSearch(int bandId, int cityId) {
        if (bandId == 0) {
            return artistRepository.findByOriginId(cityId);
        } else if (cityId == 0) {
            return ((List<ArtistActivity>)activityRepository.findByBandId(bandId))
                    .stream()
                    .map(activity -> activity.getArtist())
                    .collect(Collectors.toSet());
        } else {
            return artistRepository.findByBandAndCity(bandId,cityId);
        }
    }
}
