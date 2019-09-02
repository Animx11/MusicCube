package musiccube.entities;

import javax.persistence.*;

@Entity
@Table(name = "artist_instrument")
public class ArtistInstrument {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Instrument instrument;

    public ArtistInstrument() {}

    public ArtistInstrument(Artist artist, Instrument instrument) {
        this.artist = artist;
        this.instrument = instrument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
