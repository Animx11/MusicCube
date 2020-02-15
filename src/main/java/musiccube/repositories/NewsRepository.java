package musiccube.repositories;

import musiccube.entities.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface NewsRepository extends CrudRepository<News, Integer>, PagingAndSortingRepository<News, Integer> {

    @Query("SELECT n FROM News n WHERE LOWER(n.newsTitle) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<News> findByNewsTitle(String newsTitle);

    @Query("SELECT n FROM News n WHERE LOWER(n.creator) LIKE LOWER(?1)")
    Iterable<News> findByCreator(String creator);



}
