package MusicCube.services.genreinstrument;

import MusicCube.entities.GenreInstrument;

import java.util.Optional;

public interface GenreInstrumentService {

    Optional<GenreInstrument> getById(int id);
    Iterable<GenreInstrument> getAll();
    GenreInstrument save(GenreInstrument genreInstrument);
    void delete(int id);

}