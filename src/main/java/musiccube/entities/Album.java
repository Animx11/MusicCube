package musiccube.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Album {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String albumName;

    @Column
    private int albumLengthSeconds;

    @Column
    private Date releaseDate;

    @Column
    private String company;

    public Album() {}

    public Album(String albumName, int albumLengthSeconds, String company) {
        this.albumName = albumName;
        this.albumLengthSeconds = albumLengthSeconds;
        this.company = company;
    }

    public Album(String albumName, int albumLengthSeconds, Date releaseDate, String company) {
        this.albumName = albumName;
        this.albumLengthSeconds = albumLengthSeconds;
        this.releaseDate = releaseDate;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumLengthSeconds() {
            return albumLengthSeconds;
    }

    public void setAlbumLengthSeconds(int albumLengthSeconds) {
        this.albumLengthSeconds = albumLengthSeconds;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}