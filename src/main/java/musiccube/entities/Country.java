package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "country", schema = "music_cube")
public class Country {

    @Id
    @Column(name = "country_id")
    @GeneratedValue
    private int id;

    @Column(name = "country_name", unique = true)
    private String countryName;

    @Column(name = "code", unique = true)
    private String code;

    public Country() {
    }

    public Country(String countryName, String code) {
        this.countryName = countryName;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
