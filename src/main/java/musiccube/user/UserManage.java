package musiccube.user;

import musiccube.entities.Role;
import musiccube.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserManage {

    private int id;
    private String userName;
    private String email;
    private Set<Role> roles = new HashSet<>();

    // Constructors

    public UserManage() {
    }

    public UserManage(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public UserManage(int id, String userName, String email, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;

    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
