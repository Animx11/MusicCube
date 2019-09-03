package musiccube.repositories;

import musiccube.entities.Concert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConcertRepository extends CrudRepository<Concert,Integer> {
    @Query("SELECT c FROM Concert c WHERE LOWER(c.concertName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Concert> findByConcertName(String concertName);
}