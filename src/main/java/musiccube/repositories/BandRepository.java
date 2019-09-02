package musiccube.repositories;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface BandRepository extends CrudRepository<Band,Integer>, PagingAndSortingRepository<Band,Integer> {

    @Query("SELECT b FROM Band b WHERE LOWER(b.bandName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Band> findByBandName(String bandName);

    @Query("SELECT b FROM Band b WHERE b.formedIn.cityName LIKE ?1")
    Iterable<Band> findByCity(String cityName);

    @Query("SELECT DISTINCT s.genre FROM Song s WHERE s.band.id=?1")
    Iterable<Genre> findBandGenres(int bandId);
    @Query("SELECT DISTINCT s.album FROM Song s WHERE s.band.id=?1 ORDER BY s.album.releaseDate")
    Iterable<Album> findBandAlbums(int bandId);

    boolean existsByBandName(String bandName);
    boolean existsByMbId(String mbid);

    @Query("SELECT DISTINCT s.band FROM Song s WHERE s.genre.genreName LIKE :genre AND s.band.formedIn.country.id=:countryID")
    Iterable<Band> findByCountryAndGenre(
            @Param("genre") String genre,
            @Param("countryID") int countryId
    );
    @Query("SELECT DISTINCT s.band FROM Song s WHERE s.genre.genreName LIKE :genre AND s.band.formedIn.id=:cityID")
    Iterable<Band> findByCityAndGenre(
            @Param("genre") String genre,
            @Param("cityID") int cityId
    );
    @Query("SELECT DISTINCT s.band FROM Song s WHERE s.genre.genreName LIKE :genre")
    Iterable<Band> findByGenreName(@Param("genre") String genre);
}
