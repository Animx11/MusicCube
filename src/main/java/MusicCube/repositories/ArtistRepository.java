package MusicCube.repositories;

import MusicCube.entities.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {

    Iterable<Artist> findByStageName(String stageName);
}
