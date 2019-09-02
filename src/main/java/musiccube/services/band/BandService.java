package musiccube.services.band;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Genre;

import java.util.Optional;

public interface BandService {

    Optional<Band> getById(int id);

    Iterable<Band> getAll();

    Iterable<Band> getAllPaging(Integer pageNr, Integer perPage);

    Band save(Band band);

    void delete(int id);

    Iterable<Band> getByBandName(String bandName);

    Iterable<Band> getByCity(String cityName);

    Iterable<Genre> getBandGenres(int bandId);

    Iterable<Album> getBandAlbums(int bandId);

    boolean existsByBandName(String bandName);

    boolean existsByMbId(String mbid);

    Iterable<Band> advancedSearch(int cityId, int countryId, String genre);
}
