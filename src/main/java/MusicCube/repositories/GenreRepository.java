package MusicCube.repositories;

import MusicCube.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre,Integer> {
    Iterable<Genre> findByGenreName(String genreName);
}
