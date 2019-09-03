package musiccube.entities;

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
    private City concertCity;


    public Concert() {}

    public Concert(Date startTime, String concertName, City concertCity) {
        this.startTime = startTime;
        this.concertName = concertName;
        this.concertCity = concertCity;
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

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public City getConcertCity() {
        return concertCity;
    }

    public void setConcertCity(City concertCity) {
        this.concertCity = concertCity;
    }
}
