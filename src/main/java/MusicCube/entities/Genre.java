package MusicCube.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Localization origin;

    @Column
    private String genreName;

    @Column
    private Date creationDate;

    public Genre() {}

    public Genre(Localization origin, String genreName, Date creationDate) {
        this.origin = origin;
        this.genreName = genreName;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Localization getOrigin() {
        return origin;
    }

    public void setOrigin(Localization origin) {
        this.origin = origin;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
