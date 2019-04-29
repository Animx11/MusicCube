package MusicCube.services.genre;

import MusicCube.entities.Genre;

import java.util.Optional;

public interface GenreService {

    Optional<Genre> getById(int id);
    Iterable<Genre> getAll();
    Genre save(Genre genre);
    void delete(int id);

    Iterable<Genre> getByGenreName(String genreName);
}