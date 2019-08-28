package musiccube.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserFavorites {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesSongs",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> favoriteSongs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesAlbums",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> favoriteAlbums = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesBands",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Set<Band> favoriteBands = new HashSet<>();

    public UserFavorites() {
    }

    public UserFavorites(User user, Set<Song> favoriteSongs, Set<Album> favoriteAlbums, Set<Band> favoriteBands) {
        this.user = user;
        this.favoriteSongs = favoriteSongs;
        this.favoriteAlbums = favoriteAlbums;
        this.favoriteBands = favoriteBands;
    }

    public UserFavorites(User user) {
        this.user = user;
        favoriteSongs.add(null);
        favoriteAlbums.add(null);
        favoriteBands.add(null);
    }


    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public void setFavoriteSongs(Set<Song> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    public Set<Album> getFavoriteAlbums() {
        return favoriteAlbums;
    }

    public void setFavoriteAlbums(Set<Album> favoriteAlbums) {
        this.favoriteAlbums = favoriteAlbums;
    }

    public Set<Band> getFavoriteBands() {
        return favoriteBands;
    }

    public void setFavoriteBands(Set<Band> favoriteBands) {
        this.favoriteBands = favoriteBands;
    }

    public void addSongToFavorites(Song song) {
        favoriteSongs.add(song);
    }

    public void addAlbumToFavorites(Album album) {
        favoriteAlbums.add(album);
    }

    public void addBandToFavorites(Band band) {
        favoriteBands.add(band);
    }

    public boolean deleteSongFromFavorites(Song song){
        return favoriteSongs.remove(song);
    }

    public boolean deleteAlbumFromFavorites(Album album){
        return favoriteAlbums.remove(album);
    }

    public boolean deleteBandFromFavorites(Band band){
        return favoriteBands.remove(band);
    }

}
