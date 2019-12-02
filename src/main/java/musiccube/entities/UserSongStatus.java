package musiccube.entities;

import javax.persistence.*;

@Entity
public class UserSongStatus {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Song song;
    @ManyToOne
    private User user;

    @Column
    private boolean listened;
    @Column
    private boolean toListen;

    public int getId() {
        return id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isListened() {
        return listened;
    }

    public void setListened(boolean listened) {
        this.listened = listened;
    }

    public boolean isToListen() {
        return toListen;
    }

    public void setToListen(boolean toListen) {
        this.toListen = toListen;
    }
}
