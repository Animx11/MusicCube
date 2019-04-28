package MusicCube.entities;

import javax.persistence.*;

@Entity
public class GenreInstrument {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Instrument instrument;

    public GenreInstrument() {}

    public GenreInstrument(Genre genre, Instrument instrument) {
        this.genre = genre;
        this.instrument = instrument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
