package musiccube.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_favorites")
public class UserFavorites {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesSongs",
        joinColumns = @JoinColumn(referencedColumnName = "user_id"),
        inverseJoinColumns = @JoinColumn(referencedColumnName = "songid"))
    private Set<Song> favoriteSongs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesAlbums",
            joinColumns = @JoinColumn(referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "albumid"))
    private Set<Album> favoriteAlbums = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesBands",
            joinColumns = @JoinColumn(referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "bandid"))
    private Set<Band> favoriteBands = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userFavoritesArtist",
            joinColumns = @JoinColumn(referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "personid"))
    private Set<Artist> favoriteArtists = new HashSet<>();


    public UserFavorites() {
    }

    public UserFavorites(User user, Set<Song> favoriteSongs, Set<Album> favoriteAlbums, Set<Band> favoriteBands, Set<Artist> favoriteArtists) {
        this.user = user;
        this.favoriteSongs = favoriteSongs;
        this.favoriteAlbums = favoriteAlbums;
        this.favoriteBands = favoriteBands;
        this.favoriteArtists = favoriteArtists;
    }

    public UserFavorites(User user) {
        this.user = user;
        favoriteSongs.add(null);
        favoriteAlbums.add(null);
        favoriteBands.add(null);
        favoriteArtists.add(null);
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

    public Set<Artist> getFavoriteArtists() {
        return favoriteArtists;
    }

    public void setFavoriteArtists(Set<Artist> favoriteArtists) {
        this.favoriteArtists = favoriteArtists;
    }

    // Add and delete

    public void addSongToFavorites(Song song) {
        favoriteSongs.add(song);
    }

    public void addAlbumToFavorites(Album album) {
        favoriteAlbums.add(album);
    }

    public void addBandToFavorites(Band band) {
        favoriteBands.add(band);
    }

    public void addArtistToFavorites(Artist artist) {
        favoriteArtists.add(artist);
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

    public boolean deleteArtistFromFavorites(Artist artist){
        return favoriteArtists.remove(artist);
    }


}
