package MusicCube.services.album;

import MusicCube.entities.Album;
import MusicCube.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
