package musiccube.entities;

import javax.persistence.*;

@Entity
public class UserAlbumStatus {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne
    private Album album;
    @ManyToOne
    private User user;

    @Column
    private boolean owned;
    @Column
    private boolean sought;

    public int getId() {
        return id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isSought() {
        return sought;
    }

    public void setSought(boolean sought) {
        this.sought = sought;
    }
}
