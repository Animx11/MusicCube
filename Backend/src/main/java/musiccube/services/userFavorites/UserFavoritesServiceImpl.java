package musiccube.services.userFavorites;

import musiccube.entities.*;
import musiccube.repositories.UserFavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserFavoritesServiceImpl implements UserFavoritesService{

    @Autowired
    private UserFavoritesRepository userFavoritesRepository;

    @Override
    public UserFavorites save(UserFavorites userFavorites){
        return userFavoritesRepository.save(userFavorites);
    }

    @Override
    public void delete(Integer id){
        userFavoritesRepository.deleteById(id);
    }

    @Override
    public Optional<UserFavorites> getById(int id){
        return userFavoritesRepository.findById(id);
    }

    @Override
    public Optional<UserFavorites> getUserFavoriteAllByUserName(String userName){
        return userFavoritesRepository.findUserFavoriteAllByUserName(userName);
    }

    @Override
    public Iterable<UserFavorites> getAll(){
        return userFavoritesRepository.findAll();
    }

    @Override
    public Set<Song> getUserFavoriteSongsByUserName(String userName) {
        return userFavoritesRepository.findUserFavoriteSongsByUserName(userName);
    }

    @Override
    public Set<Album> getUserFavoriteAlbumsByUserName(String userName) {
        return userFavoritesRepository.findUserFavoriteAlbumsByUserName(userName);
    }

    @Override
    public Set<Band> getUserFavoriteBandsByUserName(String userName) {
        return userFavoritesRepository.findUserFavoriteBandsByUserName(userName);
    }

    @Override
    public Set<Artist> getUserFavoriteArtistsByUserName(String userName) {
        return userFavoritesRepository.findUserFavoriteArtistByUserName(userName);
    }

}
