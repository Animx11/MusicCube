package MusicCube.entities;

import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String cityName;
    @ManyToOne
    private Country country;

    public City() {};

    public City(String cityName, Country country) {
        this.cityName = cityName;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
