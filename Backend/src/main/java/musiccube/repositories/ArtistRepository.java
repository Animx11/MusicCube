package musiccube.repositories;

import musiccube.entities.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends CrudRepository<Artist,Integer>, PagingAndSortingRepository<Artist,Integer> {

    @Query("SELECT a from Artist a WHERE LOWER(a.stageName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Artist> findByStageName(String stageName);
    @Query("SELECT a FROM Artist a WHERE LOWER(a.stageName) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(a.firstNames) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(a.lastName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Artist> findByAnything(String input);
    Artist findByMbId(String mbId);

    boolean existsByFirstNamesAndLastName(String firstNames, String lastNames);
    boolean existsArtistByStageName(String stageName);
    boolean existsByMbId(String mbId);

    @Query("SELECT DISTINCT aa.artist FROM ArtistActivity aa WHERE aa.band.id = :band " +
            "AND aa.artist.origin.country.id = :country " +
            "AND aa.artist.id IN (SELECT ai.artist.id FROM ArtistInstrument ai WHERE ai.instrument.id=:instrument)"
    )
    Iterable<Artist> findByBandAndCountryAndInstrument(
            @Param("band") int bandId,
            @Param("country") int countryId,
            @Param("instrument") int instrumentId
    );
    @Query("SELECT DISTINCT aa.artist FROM ArtistActivity aa WHERE aa.band.id = :band " +
            "AND aa.artist.origin.id = :city " +
            "AND aa.artist.id IN (SELECT ai.artist.id FROM ArtistInstrument ai WHERE ai.instrument.id=:instrument)"
    )
    Iterable<Artist> findByBandAndCityAndInstrument(
            @Param("band") int bandId,
            @Param("city") int cityId,
            @Param("instrument") int instrumentId
    );
    @Query("SELECT DISTINCT aa.artist FROM ArtistActivity aa WHERE aa.band.id = :band AND aa.artist.origin.country.id = :country")
    Iterable<Artist> findByBandAndCountry(
            @Param("band") int bandId,
            @Param("country") int countryId
    );
    @Query("SELECT DISTINCT aa.artist FROM ArtistActivity aa WHERE aa.band.id = :band AND aa.artist.origin.id = :city")
    Iterable<Artist> findByBandAndCity(
            @Param("band") int bandId,
            @Param("city") int cityId
    );
    @Query("SELECT DISTINCT aa.artist FROM ArtistActivity aa WHERE aa.band.id = :band AND aa.artist.id IN (SELECT ai.artist.id FROM ArtistInstrument ai WHERE ai.instrument.id=:instrument)")
    Iterable<Artist> findByBandAndInstrument(
            @Param("band") int bandId,
            @Param("instrument") int instrumentId
    );
    @Query("SELECT DISTINCT ai.artist FROM ArtistInstrument ai WHERE ai.instrument.id=:instrument AND ai.artist.origin.country.id=:country")
    Iterable<Artist> findByCountryAndInstrument(
            @Param("country") int countryId,
            @Param("instrument") int instrumentId
    );
    @Query("SELECT DISTINCT ai.artist FROM ArtistInstrument ai WHERE ai.instrument.id=:instrument AND ai.artist.origin.id=:city")
    Iterable<Artist> findByCityAndInstrument(
            @Param("city") int cityId,
            @Param("instrument") int instrumentId
    );
    Iterable<Artist> findByOriginId(int id);
    Iterable<Artist> findByOriginCountryId(int id);
    @Query("SELECT DISTINCT ai.artist FROM ArtistInstrument ai WHERE ai.instrument.id=:id")
    Iterable<Artist> findByInstrument(@Param("id") int id);




}
