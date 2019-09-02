package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue
    @Column(name = "song_id")
    private int id;

    @Column
    private String songName;

    @Column
    private int trackNumber;

    @Column
    private int songLengthSeconds;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "band_id", referencedColumnName = "band_id")
    private Band band;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;

    public Song() {}

    public Song(String songName, int trackNumber, int songLengthSeconds, Album album, Band band, Genre genre) {
        this.songName = songName;
        this.trackNumber = trackNumber;
        this.songLengthSeconds = songLengthSeconds;
        this.album = album;
        this.band = band;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getSongLengthSeconds() {
        return songLengthSeconds;
    }

    public void setSongLengthSeconds(int songLengthSeconds) {
        this.songLengthSeconds = songLengthSeconds;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}