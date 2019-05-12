package MusicCube.entities;

import javax.persistence.*;

@Entity
public class Song {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String songName;

    @Column
    private int songLengthSeconds;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Band band;

    @ManyToOne
    private Genre genre;

    public Song() {}

    public Song(String songName, int songLengthSeconds, Album album, Band band, Genre genre) {
        this.songName = songName;
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