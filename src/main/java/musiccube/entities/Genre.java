package musiccube.entities;

import javax.persistence.*;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Country origin;

    @Column
    private String genreName;

    @Column
    private String creationDate;

    public Genre() {}

    public Genre(Country origin, String genreName, String creationDate) {
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

    public Country getOrigin() {
        return origin;
    }

    public void setOrigin(Country origin) {
        this.origin = origin;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
