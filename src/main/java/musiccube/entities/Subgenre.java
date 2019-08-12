package musiccube.entities;

import javax.persistence.*;

@Entity
public class Subgenre {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Genre parentGenre;

    @ManyToOne
    private Genre childGenre;

    public Subgenre() {}

    public Subgenre(Genre parentGenre, Genre childGenre) {
        this.parentGenre = parentGenre;
        this.childGenre = childGenre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genre getParentGenre() {
        return parentGenre;
    }

    public void setParentGenre(Genre parentGenre) {
        this.parentGenre = parentGenre;
    }

    public Genre getChildGenre() {
        return childGenre;
    }

    public void setChildGenre(Genre childGenre) {
        this.childGenre = childGenre;
    }
}
