package musiccube.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "roleName")
    private RoleName name;

    // Constructors

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    // Getters

    public int getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }

    // Setters

    public void setName(RoleName name) {
        this.name = name;
    }
}
