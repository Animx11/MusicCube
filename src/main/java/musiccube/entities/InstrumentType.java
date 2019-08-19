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
    private String instrumentTypeName;

    @Column
    private String aboutType;

    public InstrumentType() {
    }

    public InstrumentType(String typeName) {
        this.instrumentTypeName = typeName;
    }

    public int getId() {
        return id;
    }

    public String getInstrumentTypeName() {
        return instrumentTypeName;
    }

    public String getAboutType() {
        return aboutType;
    }

    public void setInstrumentTypeName(String instrumentTypeName) {
        this.instrumentTypeName = instrumentTypeName;
    }

    public void setAboutType(String aboutType) {
        this.aboutType = aboutType;
    }

}
