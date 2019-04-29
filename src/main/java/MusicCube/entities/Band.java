package MusicCube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Band {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String bandName;

    @Column
    private Date creationDate;

    @ManyToOne
    private Location formedIn;

    public Band() {}

    public Band(String bandName, Date creationDate, Location formedIn) {
        this.bandName = bandName;
        this.creationDate = creationDate;
        this.formedIn = formedIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Location getFormedIn() {
        return formedIn;
    }

    public void setFormedIn(Location formedIn) {
        this.formedIn = formedIn;
    }
}
