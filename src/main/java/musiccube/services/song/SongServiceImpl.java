package musiccube.services.song;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;
import musiccube.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Optional<Song> getById(int id) {
        return songRepository.findById(id);
    }

    @Override
    public Iterable<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public Iterable<Song> getAllPaging(Integer pageNr, Integer perPage) {
        return songRepository.findAll(new PageRequest(pageNr,perPage));
    }
    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }
    @Override
    public void delete(int id) {
        songRepository.deleteById(id);
    }

    @Override
    public Iterable<Song> getBySongName(String songName) {
        return songRepository.findBySongName(songName);
    }

    @Override
    public Iterable<Song> getByGenreName(String genreName) {
        return songRepository.findByGenreName(genreName);
    }

    @Override
    public Iterable<Song> getByAlbumName(String albumName) {
        return songRepository.findByAlbumName(albumName);
    }

    @Override
    public Iterable<Song> getByBandId(int id) {
        return songRepository.findByBandId(id);
    }

    @Override
    public boolean existsBySongName(String songName){
        return songRepository.existsBySongName(songName);
    }
    @Override
    public boolean existsByAlbum(Album album){
        return songRepository.existsByAlbum(album);
    }
    @Override
    public boolean existsByBand(Band band){
        return songRepository.existsByBand(band);
    }

}
