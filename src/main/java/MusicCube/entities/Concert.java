package MusicCube.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Concert {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private Date startTime;

    @Column
    private String concertName;

    @ManyToOne
    private Location location;

    public Concert() {}

    public Concert(Date startTime, Location location, String concertName) {
        this.startTime = startTime;
        this.location = location;
        this.concertName = concertName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }
}
