package musiccube.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "instrument")
public class Instrument {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column(unique = true)
    private String instrumentName;

    @Column(length = 1024)
    private String aboutInstrument;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private InstrumentType instrumentType;

    public Instrument() {}

    public Instrument(String instrumentName, InstrumentType instrumentType) {
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getAboutInstrument() {
        return aboutInstrument;
    }

    public void setAboutInstrument(String aboutInstrument) {
        this.aboutInstrument = aboutInstrument;
    }
}
