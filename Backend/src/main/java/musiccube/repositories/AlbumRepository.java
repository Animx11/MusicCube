package musiccube.repositories;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AlbumRepository extends CrudRepository<Album,Integer>, PagingAndSortingRepository<Album,Integer> {

    @Query("SELECT a FROM Album a WHERE LOWER(a.albumName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Album> findByAlbumName(String albumName);
    @Query("SELECT DISTINCT s.band FROM Song s WHERE s.album.id=?1")
    Iterable<Band> findAlbumBands(int albumId);
    @Query("SELECT s FROM Song s WHERE s.album.id=?1 ORDER BY s.trackNumber")
    Iterable<Song> findAlbumSongs(int albumId);

    boolean existsByAlbumName(String albumName);


    @Query("SELECT DISTINCT s.album from Song s WHERE s.songName LIKE :song AND s.genre.genreName LIKE :genre AND LOWER(s.album.company) LIKE LOWER(:company)")
    Iterable<Album> advancedSearch(
            @Param("song") String song,
            @Param("genre") String genre,
            @Param("company") String company
    );
}
