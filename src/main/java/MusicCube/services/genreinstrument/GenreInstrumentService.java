package MusicCube.services.genreinstrument;

import MusicCube.entities.Genre;
import MusicCube.entities.GenreInstrument;
import MusicCube.entities.Instrument;

import java.util.Optional;

public interface GenreInstrumentService {

    Optional<GenreInstrument> getById(int id);
    Iterable<GenreInstrument> getAll();
    GenreInstrument save(GenreInstrument genreInstrument);
    void delete(int id);

    Iterable<GenreInstrument> getByGenre(Genre genre);
    Iterable<GenreInstrument> getByInstrument(Instrument instrument);

}