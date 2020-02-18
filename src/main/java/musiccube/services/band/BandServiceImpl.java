package musiccube.services.band;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Genre;
import musiccube.recommendations.RecommendationsIdListBuilder;
import musiccube.repositories.BandRepository;
import musiccube.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;
    @Autowired
    private RateRepository rateRepository;

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
    public Iterable<Band> getByCountry(String countryName) {
        return bandRepository.findByFormedInCountryCountryName(countryName);
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

    @Override
    public Iterable<Band> advancedSearch(int cityId, int countryId, String genre) {
        if (cityId == 0) {
            if (countryId == 0) {
                if (genre.equals("%")) {
                    return new ArrayList<>();
                } else {
                    return bandRepository.findByGenreName(genre);
                }
            } else {
                return bandRepository.findByCountryAndGenre(genre, countryId);
            }
        } else if (countryId == 0) {
            return bandRepository.findByCityAndGenre(genre, cityId);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Iterable<Band> getSimilar(int bandId, int limit, Optional<String> userName) {
        List<Band> results = new ArrayList<>();
        List<Set<Integer>> idSets = new ArrayList<>();

        Band band = getById(bandId).orElse(null);
        if (band == null) return results;

        HashSet<Integer> sameCity = new HashSet<>();
        HashSet<Integer> sameCountry =  new HashSet<>();
        HashSet<Integer> sameGenres = new HashSet<>();
        HashSet<Integer> sameEra = new HashSet<>();

        findBandsSameArea(band, sameCity, sameCountry);

        findBandsWithSameGenres(bandId, sameGenres);

        findBandsSameEra(band, sameEra);

        if (userName.isPresent()) {
            String user = userName.get();
            removeAlreadyRated(sameCity, sameCountry, sameGenres, sameEra, user);
        }

        idSets.add(sameCity);
        idSets.add(sameCountry);
        idSets.add(sameGenres);
        idSets.add(sameEra);

        List<Integer> ids = RecommendationsIdListBuilder.build(idSets,limit);

        results = (List<Band>) bandRepository.findAllById(ids);

        return results;
    }

    private void removeAlreadyRated(HashSet<Integer> sameCity, HashSet<Integer> sameCountry, HashSet<Integer> sameGenres, HashSet<Integer> sameEra, String user) {
        Collection<Integer> ratedIds = (Collection<Integer>) rateRepository.findBandIdsByUserName(user);
        sameCity.retainAll(ratedIds);
        sameCountry.retainAll(ratedIds);
        sameGenres.retainAll(ratedIds);
        sameEra.retainAll(ratedIds);
    }

    private void findBandsSameEra(Band band, HashSet<Integer> sameEra) {
        if (band.getCreationDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(band.getCreationDate());
            calendar.add(Calendar.YEAR,10);
            Date date1 = calendar.getTime();
            calendar.add(Calendar.YEAR, -20);
            Date date2 = calendar.getTime();

            sameEra.addAll(
                    StreamSupport.stream(bandRepository.findByDate(date1,date2).spliterator(),false)
                    .map(Band::getId)
                    .collect(Collectors.toSet())
            );
            sameEra.remove(band.getId());
        }
    }

    private void findBandsSameArea(Band band, HashSet<Integer> sameCity, HashSet<Integer> sameCountry) {
        if (band.getFormedIn() != null) {
            findBandsFromSameCity(band, sameCity);
            findBandsFromSameCountry(band, sameCountry);
        }
    }

    private void findBandsWithSameGenres(int bandId, HashSet<Integer> sameGenres) {
        ArrayList<Genre> genres = (ArrayList<Genre>)(getBandGenres(bandId));
        if (! genres.isEmpty()) {
            genres.forEach(
                    genre -> sameGenres.addAll(
                            StreamSupport.stream(bandRepository.findByGenreName(genre.getGenreName()).spliterator(),false)
                            .map(Band::getId)
                            .collect(Collectors.toSet())
                    )
            );
        }
        sameGenres.remove(bandId);
    }

    private void findBandsFromSameCountry(Band band, HashSet<Integer> sameCountry) {
        sameCountry.addAll(
                StreamSupport.stream(getByCountry(band.getFormedIn().getCountry().getCountryName()).spliterator(),false)
                .map(Band::getId)
                .collect(Collectors.toSet())
        );
        sameCountry.remove(band.getId());
    }

    private void findBandsFromSameCity(Band band, HashSet<Integer> sameCity) {
        sameCity.addAll(
                StreamSupport.stream(getByCity(band.getFormedIn().getCityName()).spliterator(),false)
                .map(Band::getId)
                .collect(Collectors.toSet())
        );
        sameCity.remove(band.getId());
    }
}
