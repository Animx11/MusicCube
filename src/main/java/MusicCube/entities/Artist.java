package MusicCube.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Artist extends Person {

    @Column
    private String stageName;

    public Artist() {}

    public Artist(Localization origin, String firstNames, String lastName, Date birthDate, String stageName) {
        super(origin, firstNames, lastName, birthDate);
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}