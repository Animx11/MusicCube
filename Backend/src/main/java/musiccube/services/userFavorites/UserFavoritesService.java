package musiccube.services.userFavorites;

import musiccube.entities.*;

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
    Set<Artist> getUserFavoriteArtistsByUserName(String userName);


}
