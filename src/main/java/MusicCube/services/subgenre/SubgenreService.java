package MusicCube.services.subgenre;

import MusicCube.entities.Genre;
import MusicCube.entities.Subgenre;

import java.util.Optional;

public interface SubgenreService {

    Optional<Subgenre> getById(int id);
    Iterable<Subgenre> getAll();
    Subgenre save(Subgenre subgenre);
    void delete(int id);

    Iterable<Subgenre> getByChildGenre(Genre childGenre);
    Iterable<Subgenre> getByParentGenre(Genre parentGenre);

}