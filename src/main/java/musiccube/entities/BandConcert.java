package musiccube.entities;

import javax.persistence.*;

@Entity
@Table(name = "band_concert")
public class BandConcert {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Band band;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
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
