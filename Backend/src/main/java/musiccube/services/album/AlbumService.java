package musiccube.services.album;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;

import java.util.Optional;

public interface AlbumService {

    Optional<Album> getById(int id);
    Iterable<Album> getAll();
    Iterable<Album> getAllPaging(Integer pageNr,Integer perPage);
    Album save(Album album);
    void delete(int id);

    Iterable<Album> getByAlbumName(String albumName);
    Iterable<Band> getAlbumBands(int albumId);
    Iterable<Song> getAlbumSongs(int albumId);
    boolean existsByAlbumName(String albumName);

    Iterable<Album> advancedSearch(String song, String genre, String company);

}