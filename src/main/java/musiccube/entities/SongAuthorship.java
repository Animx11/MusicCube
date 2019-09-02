package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "song_authorship")
public class SongAuthorship {

    @Id
    @GeneratedValue
    @Column(name = "song_authorship_id")
    private int id;

    @Column
    private boolean wroteText;

    @Column
    private boolean wroteMusic;

    @ManyToOne
    @JoinColumn(name = "song_id", referencedColumnName = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person author;

    public SongAuthorship() {}

    public SongAuthorship(boolean wroteText, boolean wroteMusic, Song song, Person author) {
        this.wroteText = wroteText;
        this.wroteMusic = wroteMusic;
        this.song = song;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWroteText() {
        return wroteText;
    }

    public void setWroteText(boolean wroteText) {
        this.wroteText = wroteText;
    }

    public boolean isWroteMusic() {
        return wroteMusic;
    }

    public void setWroteMusic(boolean wroteMusic) {
        this.wroteMusic = wroteMusic;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
