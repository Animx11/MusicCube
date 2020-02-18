package musiccube.services.artist;

import musiccube.entities.Artist;
import musiccube.entities.ArtistActivity;
import musiccube.repositories.ArtistActivityRepository;
import musiccube.repositories.ArtistRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    ArtistActivityRepository activityRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

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

    @Override
    public Iterable<Artist> advancedSearch(Optional<Integer> bandId, Optional<Integer> countryId, Optional<Integer> cityId, Optional<Integer> instrumentId) {
        if (countryId.isPresent() && cityId.isPresent()) {
            return new ArrayList<>();
        } else if (bandId.isPresent() && countryId.isPresent() && instrumentId.isPresent()) {
            return artistRepository.findByBandAndCountryAndInstrument(
                    bandId.get(),
                    countryId.get(),
                    instrumentId.get()
            );
        } else if (bandId.isPresent() && cityId.isPresent() && instrumentId.isPresent()) {
            return artistRepository.findByBandAndCityAndInstrument(
                    bandId.get(),
                    cityId.get(),
                    instrumentId.get()
            );
        } else if(bandId.isPresent() && countryId.isPresent()) {
            artistRepository.findByBandAndCountry(bandId.get(), countryId.get());
        } else if(bandId.isPresent() && cityId.isPresent()) {
            artistRepository.findByBandAndCity(bandId.get(), cityId.get());
        } else if(bandId.isPresent() && instrumentId.isPresent()) {
            return artistRepository.findByBandAndInstrument(bandId.get(), instrumentId.get());
        } else if(countryId.isPresent() && instrumentId.isPresent()) {
            return artistRepository.findByCountryAndInstrument(countryId.get(), instrumentId.get());
        } else if(cityId.isPresent() && instrumentId.isPresent()) {
            return artistRepository.findByCityAndInstrument(cityId.get(), instrumentId.get());
        } else if(bandId.isPresent()) {
            return ((ArrayList<ArtistActivity>) activityRepository.findByBandId(bandId.get()))
                    .stream()
                    .map(activity -> activity.getArtist())
                    .collect(Collectors.toList());
        } else if (cityId.isPresent()) {
            return artistRepository.findByOriginId(cityId.get());
        } else if (countryId.isPresent()) {
            return artistRepository.findByOriginCountryId(countryId.get());
        } else if (instrumentId.isPresent()) {
            return artistRepository.findByInstrument(instrumentId.get());
        }
        return new ArrayList<>();
    }

    @Override
    public Iterable<Artist> advanced(Map<String, String> params) {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        return null;
    }
}
