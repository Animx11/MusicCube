package MusicCube.repositories;

import MusicCube.entities.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {

    @Query("SELECT a from Artist a WHERE a.stageName LIKE CONCAT('%',?1,'%')")
    Iterable<Artist> findByStageName(String stageName);

    boolean existsByFirstNamesAndLastName(String firstNames, String lastNames);
    boolean existsArtistByStageName(String stageName);

}
