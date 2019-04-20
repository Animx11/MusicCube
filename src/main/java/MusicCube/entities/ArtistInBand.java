package MusicCube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class ArtistInBand {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Band band;

    @Column
    private String yearsActive;

    @Column
    private boolean isActive;

    public ArtistInBand() {}

    public ArtistInBand(Artist artist, Band band, String yearsActive) {
        this.artist = artist;
        this.band = band;
        this.yearsActive = yearsActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public String getYearsActive() {
        return yearsActive;
    }

    public void setYearsActive(String yearsActive) {
        this.yearsActive = yearsActive;
    }
}
