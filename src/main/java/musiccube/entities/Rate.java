package musiccube.entities;

import javax.persistence.*;

@Entity
public class Rate {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;

    @Column
    private int rate;

    public Rate() {
    }

    public Rate(User user, Song song, int rate) {
        this.user = user;
        this.song = song;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
