package musiccube.services.band;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Genre;
import musiccube.entities.Rate;
import musiccube.recommendations.RecommendationsIdListBuilder;
import musiccube.repositories.BandRepository;
import musiccube.repositories.RateRepository;
import org.apache.log4j.Logger;
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
    private Logger logger = Logger.getLogger(BandServiceImpl.class);

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

        Band band = getById(bandId).orElse(null);
        if (band == null) return results;

        List<Integer> ids = getSimilarBandsIds(band, limit, userName);

        results = (List<Band>) bandRepository.findAllById(ids);

        return results;
    }

    private List<Integer> getSimilarBandsIds(Band band, int limit, Optional<String> userName) {
        List<Set<Integer>> idSets = new ArrayList<>();
        HashSet<Integer> sameCity = new HashSet<>();
        HashSet<Integer> sameCountry =  new HashSet<>();
        HashSet<Integer> sameGenres = new HashSet<>();
        HashSet<Integer> sameEra = new HashSet<>();

        findBandsSameArea(band, sameCity, sameCountry);

        findBandsWithSameGenres(band, sameGenres);

        findBandsSameEra(band, sameEra);

        if (userName.isPresent()) {
            String user = userName.get();
            removeAlreadyRated(sameCity, sameCountry, sameGenres, sameEra, user);
        }

        idSets.add(sameCity);
        idSets.add(sameCountry);
        idSets.add(sameGenres);
        idSets.add(sameEra);

        return RecommendationsIdListBuilder.build(idSets,limit);
    }

    @Override
    public Iterable<Band> getRecommended(String userName, int limit) {
        List<Rate> rates = (List<Rate>) rateRepository.findByUserUserNameAndBandIsNotNull(userName);
        logger.info("user rated "+rates.size()+" bands");
        Set<Integer> recommendedSet = new HashSet<>();
        rates.stream()
                .filter(rate -> rate.getRate() > 5)
                .map(rate -> rate.getBand().getId())
                .forEach(id -> {
                    logger.info("looking for bands similar to "+id);
                    Optional<Band> band = bandRepository.findById(id);
                    band.ifPresent(band1 -> recommendedSet.addAll(getSimilarBandsIds(band1, limit, Optional.of(userName))));
                });
        List<Integer> recommendedList = new ArrayList<>(recommendedSet);
        logger.info(recommendedList);
        Random random = new Random();
        while (recommendedList.size() > limit) {
            recommendedList.remove(random.nextInt(recommendedList.size()));
        }
        return bandRepository.findAllById(recommendedList);
    }

    private void removeAlreadyRated(HashSet<Integer> sameCity, HashSet<Integer> sameCountry, HashSet<Integer> sameGenres, HashSet<Integer> sameEra, String user) {
        Collection<Integer> ratedIds = (Collection<Integer>) rateRepository.findBandIdsByUserName(user);
        sameCity.removeAll(ratedIds);
        sameCountry.removeAll(ratedIds);
        sameGenres.removeAll(ratedIds);
        sameEra.removeAll(ratedIds);
    }

    private void findBandsSameEra(Band band, HashSet<Integer> sameEra) {
        System.out.println(band.getId());
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
            System.out.println(sameEra.toString());
        }
    }

    private void findBandsSameArea(Band band, HashSet<Integer> sameCity, HashSet<Integer> sameCountry) {
        if (band.getFormedIn() != null) {
            findBandsFromSameCity(band, sameCity);
            findBandsFromSameCountry(band, sameCountry);
        }
    }

    private void findBandsWithSameGenres(Band band, HashSet<Integer> sameGenres) {
        ArrayList<Genre> genres = (ArrayList<Genre>)(getBandGenres(band.getId()));
        if (! genres.isEmpty()) {
            genres.forEach(
                    genre -> sameGenres.addAll(
                            StreamSupport.stream(bandRepository.findByGenreName(genre.getGenreName()).spliterator(),false)
                            .map(Band::getId)
                            .collect(Collectors.toSet())
                    )
            );
        }
        sameGenres.remove(band.getId());
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
