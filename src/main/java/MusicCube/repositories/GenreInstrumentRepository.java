package MusicCube.repositories;

import MusicCube.entities.Genre;
import MusicCube.entities.GenreInstrument;
import MusicCube.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface GenreInstrumentRepository extends CrudRepository<GenreInstrument,Integer> {
    Iterable<GenreInstrument> findByGenre(Genre genre);
    Iterable<GenreInstrument> findByInstrument(Instrument instrument);
}
