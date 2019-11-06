package musiccube.repositories;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SongRepository extends CrudRepository<Song,Integer>, PagingAndSortingRepository<Song, Integer> {

    @Query("SELECT s FROM Song s where LOWER(s.songName) LIKE LOWER(CONCAT('%',?1,'%'))")
    Iterable<Song> findBySongName(String songName);

    @Query("SELECT s FROM Song s where s.genre.genreName LIKE ?1")
    Iterable<Song> findByGenreName(String genreName);

    @Query("SELECT s FROM Song s where s.album.albumName LIKE ?1")
    Iterable<Song> findByAlbumName(String albumName);

    @Query("SELECT s FROM Song s where s.band.id = ?1")
    Iterable<Song> findByBandId(int id);


    boolean existsBySongName(String songName);
    boolean existsByAlbum(Album album);
    boolean existsByBand(Band band);
}
