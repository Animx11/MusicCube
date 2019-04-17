package MusicCube.Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AuthorisationToken", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class AuthorisationToken {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "code")
    private String code;

    @Column(name = "userPermission")
    private String userPermission;

    @Column(name = "expiryTime")
    private Date expiryTime;

    //Constructors

    public AuthorisationToken() {
    }

    public AuthorisationToken(String userName, String code, String userPermission, Date expiryTime) {
        this.userName = userName;
        this.code = code;
        this.userPermission = userPermission;
        this.expiryTime = expiryTime;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getCode() {
        return code;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public String getUserPermission() {
        return userPermission;
    }

    //Setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }
}
