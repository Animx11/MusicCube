package musiccube.repositories;

import musiccube.entities.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RateRepository extends CrudRepository<Rate, Integer>, PagingAndSortingRepository<Rate, Integer> {

    @Query("SELECT r FROM Rate r where r.user.userName like ?1 AND r.song.id = ?2")
    Optional<Rate> findRateByUserNameAndSongId(String userName, int id);

    @Query("SELECT r FROM Rate r WHERE r.user.userName like ?1")
    Iterable<Rate> findAllUserRates(String userName);

}
