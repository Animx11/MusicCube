package musiccube.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "concert", schema = "music_cube")
public class Concert {
    @Id
    @GeneratedValue
    @Column(name = "concert_id")
    private int id;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "concert_name")
    private String concertName;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
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
