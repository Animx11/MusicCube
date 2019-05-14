package MusicCube.repositories;

import MusicCube.entities.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Integer> {

    @Query("SELECT a FROM Album a WHERE a.albumName LIKE CONCAT('%',?1,'%')")
    Iterable<Album> findByAlbumName(String albumName);
}
