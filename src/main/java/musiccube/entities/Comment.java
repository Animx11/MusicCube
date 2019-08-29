package musiccube.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;

    @Column
    private Date commentDate;

    @Column
    private boolean wasEdited;

    @Column
    private String commentContent;

    public Comment() {
    }

    public Comment(User user, Song song, Date commentDate, boolean wasEdited, String commentContent) {
        this.user = user;
        this.song = song;
        this.commentDate = commentDate;
        this.wasEdited = wasEdited;
        this.commentContent = commentContent;
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

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public boolean isWasEdited() {
        return wasEdited;
    }

    public void setWasEdited(boolean wasEdited) {
        this.wasEdited = wasEdited;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

}
