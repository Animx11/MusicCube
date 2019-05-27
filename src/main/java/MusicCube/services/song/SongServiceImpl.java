package MusicCube.services.song;

import MusicCube.entities.Album;
import MusicCube.entities.Band;
import MusicCube.entities.Song;
import MusicCube.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
