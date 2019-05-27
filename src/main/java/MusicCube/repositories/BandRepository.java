package MusicCube.repositories;

import MusicCube.entities.Album;
import MusicCube.entities.Band;
import MusicCube.entities.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BandRepository extends CrudRepository<Band,Integer> {

    @Query("SELECT b FROM Band b WHERE b.bandName LIKE CONCAT ('%',?1,'%')")
    Iterable<Band> findByBandName(String bandName);
    @Query("SELECT DISTINCT s.genre FROM Song s WHERE s.band.id=?1")
    Iterable<Genre> findBandGenres(int bandId);
    @Query("SELECT DISTINCT s.album FROM Song s WHERE s.band.id=?1 ORDER BY s.album.releaseDate")
    Iterable<Album> findBandAlbums(int bandId);

    boolean existsByBandName(String bandName);

}
