package MusicCube.user;

import MusicCube.entities.User;

public class userAccount {

    private String userName;
    private String password;
    private String email;

    // Constructors

    public userAccount() {
    }

    public userAccount(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public userAccount(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
