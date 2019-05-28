package MusicCube.repositories;

import MusicCube.entities.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenreRepository extends CrudRepository<Genre,Integer>, PagingAndSortingRepository<Genre,Integer> {

    @Query("SELECT g FROM Genre g WHERE LOWER(g.genreName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Genre> findByGenreName(String genreName);
}
