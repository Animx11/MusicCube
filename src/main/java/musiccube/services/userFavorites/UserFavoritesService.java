package musiccube.services.userFavorites;

import musiccube.entities.Album;
import musiccube.entities.Band;
import musiccube.entities.Song;
import musiccube.entities.UserFavorites;

import java.util.Optional;
import java.util.Set;

public interface UserFavoritesService {

    UserFavorites save(UserFavorites userFavorites);
    void delete(Integer id);

    Optional<UserFavorites> getById(int id);
    Optional<UserFavorites> getUserFavoriteAllByUserName(String userName);
    Iterable<UserFavorites> getAll();

    Set<Song> getUserFavoriteSongsByUserName(String userName);
    Set<Album> getUserFavoriteAlbumsByUserName(String userName);
    Set<Band> getUserFavoriteBandsByUserName(String userName);

}
