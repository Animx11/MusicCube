package musiccube.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "mbId"})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "artist")
public class Artist extends Person {

    @Column(name = "stage_name")
    private String stageName;

    @Column(name = "mb_id")
    private String mbId;

    public Artist() {}

    public Artist(City origin, String firstNames, String lastName, Date birthDate, Date deathDate, String stageName) {
        super(origin, firstNames, lastName, birthDate, deathDate);
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }
}