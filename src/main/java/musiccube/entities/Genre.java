package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "genre", schema = "music_cube")
public class Genre {

    @Id
    @GeneratedValue
    @Column(name = "genre_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country origin;

    @Column(name = "genre_name", unique = true)
    private String genreName;

    @Column(name = "creation_date")
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
