package musiccube.repositories;

import musiccube.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface UserFavoritesRepository extends CrudRepository<UserFavorites, Integer> {

    @Query("SELECT favoriteSongs from UserFavorites f where f.user.userName like ?1")
    Set<Song> findUserFavoriteSongsByUserName(String userName);

    @Query("SELECT favoriteAlbums from UserFavorites f where f.user.userName like ?1")
    Set<Album> findUserFavoriteAlbumsByUserName(String userName);

    @Query("SELECT favoriteBands from UserFavorites f where f.user.userName like ?1")
    Set<Band> findUserFavoriteBandsByUserName(String userName);

    @Query("SELECT favoriteArtists from UserFavorites f where f.user.userName like ?1")
    Set<Artist> findUserFavoriteArtistByUserName(String userName);

    @Query("SELECT f from UserFavorites f where f.user.userName like ?1")
    Optional<UserFavorites> findUserFavoriteAllByUserName(String userName);


}
