package MusicCube.repositories;

import MusicCube.entities.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends CrudRepository<Artist,Integer>, PagingAndSortingRepository<Artist,Integer> {

    @Query("SELECT a from Artist a WHERE LOWER(a.stageName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Artist> findByStageName(String stageName);
    @Query("SELECT a FROM Artist a WHERE LOWER(a.stageName) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(a.firstNames) LIKE LOWER(CONCAT('%',?1,'%')) OR LOWER(a.lastName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Artist> findByAnything(String input);

    boolean existsByFirstNamesAndLastName(String firstNames, String lastNames);
    boolean existsArtistByStageName(String stageName);

}
