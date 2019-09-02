package musiccube.entities;

import javax.persistence.*;

@Entity
@Table(name = "band_concert", schema = "music_cube")
public class BandConcert {

    @Id
    @GeneratedValue
    @Column(name = "band_concert_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "band_id", referencedColumnName = "band_id")
    private Band band;

    @ManyToOne
    @JoinColumn(name = "concert_id", referencedColumnName = "concert_id")
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
