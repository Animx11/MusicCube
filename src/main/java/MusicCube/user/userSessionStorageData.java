package MusicCube.user;

import MusicCube.entities.Role;
import MusicCube.entities.User;

import java.util.HashSet;
import java.util.Set;

public class userSessionStorageData {

    private String userName;
    private String email;
    private Set<Role> roles = new HashSet<>();

    // Constructors

    public userSessionStorageData() {
    }

    public userSessionStorageData(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public userSessionStorageData(String userName, String email, Set<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    // Getters and Setters

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
