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
    private Localization localization;

    public Concert() {}

    public Concert(Date startTime, Localization localization, String concertName) {
        this.startTime = startTime;
        this.localization = localization;
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

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }
}
