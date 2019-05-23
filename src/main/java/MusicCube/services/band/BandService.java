package MusicCube.services.band;

import MusicCube.entities.Album;
import MusicCube.entities.Band;
import MusicCube.entities.Genre;

import java.util.Optional;

public interface BandService {

    Optional<Band> getById(int id);

    Iterable<Band> getAll();

    Band save(Band band);

    void delete(int id);

    Iterable<Band> getByBandName(String bandName);

    Iterable<Genre> getBandGenres(int bandId);

    Iterable<Album> getBandAlbums(int bandId);
}