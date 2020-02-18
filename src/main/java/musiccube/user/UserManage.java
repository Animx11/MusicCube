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
    private String primaryRole;

    // Constructors

    public UserManage() {
    }

    public UserManage(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public UserManage(int id, String userName, String email, Set<Role> roles, String primaryRole) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
        this.primaryRole = primaryRole;
    }

    public UserManage(int id, String userName, String email, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;

    }

    public UserManage(int id, String userName, String email, String primaryRole) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.primaryRole = primaryRole;
    }

    public int getId() {
        return id;
    }

    public String getPrimaryRole() {
        return this.primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
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
