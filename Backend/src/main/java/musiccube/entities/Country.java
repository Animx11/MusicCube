package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Country {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String countryName;

    @Column(unique = true)
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
