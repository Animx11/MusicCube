package musiccube.entities;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "person", schema = "music_cube")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    protected int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    protected City origin;

    @Column(name = "first_names")
    protected String firstNames;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "birth_date")
    protected Date birthDate;

    @Column(name = "death_date")
    protected Date deathDate;

    public Person() {}

    public Person(String firstNames, String lastName, Date birthDate) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Person(City origin, String firstNames, String lastName, Date birthDate, Date deathDate) {
        this.origin = origin;
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }
}
