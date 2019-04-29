package MusicCube.repositories;

import MusicCube.entities.Artist;
import MusicCube.entities.ArtistInBand;
import MusicCube.entities.Band;
import org.springframework.data.repository.CrudRepository;

public interface ArtistInBandRepository extends CrudRepository<ArtistInBand,Integer> {
    Iterable<ArtistInBand> findByArtist(Artist artist);
    Iterable<ArtistInBand> findByBand(Band band);
}
