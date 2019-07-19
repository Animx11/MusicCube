package MusicCube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Localization {

    @Id
    @GeneratedValue
    @Column
    private int id;

    // TODO Zmienienie bazy danych i tego entity by było połączone z entity country, też uważam, że ta klasa powinna już tylko konkretne miejscówki naprzykład areny muzyczne
    // TODO więc powinniśmy też zrobić odzielne entity dla miast, a tu dodać nazwę miejscówy.

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    public Localization() {}

    public Localization(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public Localization(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
