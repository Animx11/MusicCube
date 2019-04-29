package MusicCube.repositories;

import MusicCube.entities.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Integer> {
    Iterable<Album> findByAlbumName(String albumName);
}
