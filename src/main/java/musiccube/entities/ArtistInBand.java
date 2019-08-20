package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

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
    private Date activityStart;

    @Column
    private Date activityEnd;

    @Column
    private boolean isActive;

    @Column(length = 1024)
    private String[] roles;

    public ArtistInBand() {}

    public ArtistInBand(Artist artist, Band band, Date activityStart, Date activityEnd, boolean isActive, String[] roles) {
        this.artist = artist;
        this.band = band;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.isActive = isActive;
        this.roles = roles;
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

    public Date getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }

    public Date getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(Date activityEnd) {
        this.activityEnd = activityEnd;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
