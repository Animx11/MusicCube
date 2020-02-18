package musiccube.entities;

import javax.persistence.*;

@Entity
public class BandConcert {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Band band;

    @ManyToOne
    private Concert concert;

    public BandConcert() {}

    public BandConcert(Band band, Concert concert) {
        this.band = band;
        this.concert = concert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
