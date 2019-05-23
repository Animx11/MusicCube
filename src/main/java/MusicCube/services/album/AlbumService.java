package MusicCube.services.album;

import MusicCube.entities.Album;
import MusicCube.entities.Band;
import MusicCube.entities.Song;

import java.util.Optional;

public interface AlbumService {

    Optional<Album> getById(int id);
    Iterable<Album> getAll();
    Album save(Album album);
    void delete(int id);

    Iterable<Album> getByAlbumName(String albumName);
    Iterable<Band> getAlbumBands(int albumId);
    Iterable<Song> getAlbumSongs(int albumId);

}