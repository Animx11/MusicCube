package MusicCube.services.song;

import MusicCube.entities.Album;
import MusicCube.entities.Band;
import MusicCube.entities.Song;

import java.util.Optional;

public interface SongService {

    Optional<Song> getById(int id);
    Iterable<Song> getAll();
    Iterable<Song> getAllPaging(Integer pageNr, Integer perPage);
    Song save(Song song);
    void delete(int id);

    Iterable<Song> getBySongName(String songName);
    boolean existsBySongName(String songName);
    boolean existsByAlbum(Album album);
    boolean existsByBand(Band band);

}