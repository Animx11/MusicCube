package musiccube.services.genre;

import musiccube.entities.Genre;

import java.util.Optional;

public interface GenreService {

    Optional<Genre> getById(int id);
    Iterable<Genre> getAll();
    Iterable<Genre> getAllPaging(Integer pageNr, Integer perPage);
    Genre save(Genre genre);
    void delete(int id);

    Iterable<Genre> getByGenreName(String genreName);
    Iterable<Genre> getByGenreOrigin(String countryName);
    Genre getOneByGenreName(String genreName);
    boolean existsByName(String name);
}