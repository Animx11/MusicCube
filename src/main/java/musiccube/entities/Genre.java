package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Genre {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Country origin;

    @Column(unique = true)
    private String genreName;

    @Column
    private String creationDate;

    @Column
    private String aboutGenre;

    public Genre() {}

    public Genre(Country origin, String genreName, String creationDate, String aboutGenre) {
        this.origin = origin;
        this.genreName = genreName;
        this.creationDate = creationDate;
        this.aboutGenre = aboutGenre;
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

    public String getAboutGenre() {
        return aboutGenre;
    }

    public void setAboutGenre(String aboutGenre) {
        this.aboutGenre = aboutGenre;
    }
}
