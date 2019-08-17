package musiccube.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SystemUsers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 3, max = 50)
    @Column(name = "userName", unique = true)
    private String userName;

    @Size(min = 6, max = 100)
    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userRoles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "birthDate")
    private Date birthDate;

    //TODO
    //Po wymyśleniu odpowiedniego sposobu na przechowywanie ulubionych dodać kolumnę favorits

    @Column(name = "aboutUser")
    private String aboutUser;

    //Constructors

    public User() {
    }

    public User(@Size(min = 3, max = 50) String userName, @Size(min = 6, max = 100) String password, String email, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(@Size(min = 3, max = 50) String userName, @Size(min = 6, max = 100) String password, String email, Set<Role> roles, String firstName, String lastName, Date birthDate, String aboutUser) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.aboutUser = aboutUser;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    //Setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
