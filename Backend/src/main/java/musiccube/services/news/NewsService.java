package musiccube.services.news;

import musiccube.entities.News;

import java.util.Optional;

public interface NewsService {

    Optional<News> getById(int id);

    Iterable<News> getAll();
    Iterable<News> getAllPaging(Integer pageNr, Integer perPage);


    News save(News news);
    void delete(int id);



}
