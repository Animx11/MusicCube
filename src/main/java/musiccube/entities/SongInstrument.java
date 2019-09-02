package musiccube.entities;

import javax.persistence.*;

@Entity
public class SongInstrument{

    @Id
    @GeneratedValue
    @Column(name = "song_instrument_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "song_id", referencedColumnName = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "instrument_id", referencedColumnName = "instrument_id")
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
