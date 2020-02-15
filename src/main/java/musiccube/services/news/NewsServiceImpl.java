package musiccube.services.news;


import musiccube.entities.News;
import musiccube.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {


    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Optional<News> getById(int id) {
        return newsRepository.findById(id);
    }

    @Override
    public Iterable<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public Iterable<News> getAllPaging(Integer pageNr, Integer perPage) {
        return newsRepository.findAll(PageRequest.of(pageNr,perPage, Sort.by(Sort.Direction.DESC, "newsCreationDate")));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public void delete(int id){
        newsRepository.deleteById(id);
    }

}
