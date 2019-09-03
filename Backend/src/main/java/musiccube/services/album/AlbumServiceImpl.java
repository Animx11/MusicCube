package musiccube.services.album;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;
import musiccube.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Optional<Album> getById(int id) {
        return albumRepository.findById(id);
    }
    @Override
    public Iterable<Album> getAll() {
        return albumRepository.findAll();
    }

    @Override
    public Iterable<Album> getAllPaging(Integer pageNr, Integer perPage) {
        return albumRepository.findAll(new PageRequest(pageNr,perPage));
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }
    @Override
    public void delete(int id) {
        albumRepository.deleteById(id);
    }

    @Override
    public Iterable<Album> getByAlbumName(String albumName) {
        return albumRepository.findByAlbumName(albumName);
    }

    @Override
    public Iterable<Band> getAlbumBands(int albumId) {
        return albumRepository.findAlbumBands(albumId);
    }

    @Override
    public Iterable<Song> getAlbumSongs(int albumId) {
        return albumRepository.findAlbumSongs(albumId);
    }

    @Override
    public boolean existsByAlbumName(String albumName){
        return albumRepository.existsByAlbumName(albumName);
    }

    @Override
    public Iterable<Album> advancedSearch(String song, String genre, String company) {
        return albumRepository.advancedSearch(song,genre,company);
    }
}
