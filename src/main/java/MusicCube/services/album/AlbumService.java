package MusicCube.services.album;

import MusicCube.entities.Album;

import java.util.Optional;

public interface AlbumService {

    Optional<Album> getById(int id);
    Iterable<Album> getAll();
    Album save(Album album);
    void delete(int id);

    Iterable<Album> getByAlbumName(String albumName);

}