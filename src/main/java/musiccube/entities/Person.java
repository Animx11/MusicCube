package musiccube.entities;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Person {

    @Id
    @GeneratedValue
    @Column
    protected int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CityId",referencedColumnName = "id")
    protected City origin;

    @Column
    protected String firstNames;

    @Column
    protected String lastName;

    @Column
    protected Date birthDate;

    public Person() {}

    public Person(String firstNames, String lastName, Date birthDate) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Person(City origin, String firstNames, String lastName, Date birthDate) {
        this.origin = origin;
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.birthDate = birthDate;
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
}
