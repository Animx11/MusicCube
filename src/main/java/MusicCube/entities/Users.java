package MusicCube.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "userName", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "userPermission")
    private String userPermission;

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

    public Users() {
    }

    public Users(String userName, String password, String email, String userPermission) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userPermission = userPermission;
    }

    public Users(String userName, String password, String email, String userPermission, String firstName, String lastName, Date birthDate, String aboutUser) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userPermission = userPermission;
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

    public String getUserPermission() {
        return userPermission;
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

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
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

}
