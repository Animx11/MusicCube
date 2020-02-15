package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Song {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String songName;

    @Column
    private int trackNumber;

    @Column
    private int songLengthSeconds;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Band band;

    @ManyToOne
    private Genre genre;

    @Column
    private String songLyrics;

    public Song() {}

    public Song(String songName, int trackNumber, int songLengthSeconds, Album album, Band band, Genre genre, String songLyrics) {
        this.songName = songName;
        this.trackNumber = trackNumber;
        this.songLengthSeconds = songLengthSeconds;
        this.album = album;
        this.band = band;
        this.genre = genre;
        this.songLyrics = songLyrics;
    }

    public String getSongLyrics() {
        return songLyrics;
    }

    public void setSongLyrics(String songLyrics) {
        this.songLyrics = songLyrics;
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