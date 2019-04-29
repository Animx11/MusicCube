package MusicCube.services.genre;

import MusicCube.entities.Genre;
import MusicCube.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Optional<Genre> getById(int id) {
        return genreRepository.findById(id);
    }
    @Override
    public Iterable<Genre> getAll() {
        return genreRepository.findAll();
    }
    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
    @Override
    public void delete(int id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Iterable<Genre> getByGenreName(String genreName) {
        return genreRepository.findByGenreName(genreName);
    }
}
