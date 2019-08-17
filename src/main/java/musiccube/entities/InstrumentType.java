package musiccube.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InstrumentType {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String typeName;

    @Column
    private String aboutType;

    public InstrumentType() {
    }

    public InstrumentType(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getAboutType() {
        return aboutType;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setAboutType(String aboutType) {
        this.aboutType = aboutType;
    }

}
