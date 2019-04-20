package MusicCube.services.band;

import MusicCube.entities.Band;

import java.util.Optional;

public interface BandService {

    Optional<Band> getById(int id);
    Iterable<Band> getAll();
    Band save(Band band);
    void delete(int id);

}
