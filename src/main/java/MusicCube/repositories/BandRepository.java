package MusicCube.repositories;

import MusicCube.entities.Band;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BandRepository extends CrudRepository<Band,Integer> {

    @Query("SELECT b FROM Band b WHERE b.bandName LIKE CONCAT ('%',?1,'%')")
    Iterable<Band> findByBandName(String bandName);

    boolean existsByBandName(String bandName);

}
