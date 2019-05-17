package MusicCube.entities;

import javax.persistence.*;

@Entity
public class SongInstrument{

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Song song;

    @ManyToOne
    private Instrument instrument;

    public SongInstrument() {}

    public SongInstrument(Song song, Instrument instrument) {
        this.song = song;
        this.instrument = instrument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
