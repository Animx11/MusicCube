package musiccube.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Song song;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Album album;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Band band;

    @ManyToOne
    @JoinColumn(name = "artist", referencedColumnName = "_id")
    private Artist artist;

    @Column
    private Date commentDate;

    @Column
    private boolean wasEdited;

    @Column
    private String commentContent;

    public Comment() {
    }

    private Comment(User user, Date commentDate, boolean wasEdited, String commentContent) {
        this.user = user;
        this.commentDate = commentDate;
        this.wasEdited = wasEdited;
        this.commentContent = commentContent;
    }

    public Comment(User user, Song song, Date commentDate, boolean wasEdited, String commentContent) {
        this(user, commentDate, wasEdited, commentContent);
        this.song = song;
    }

    public Comment(User user, Album album, Date commentDate, boolean wasEdited, String commentContent) {
        this(user, commentDate, wasEdited, commentContent);
        this.album = album;
    }

    public Comment(User user, Band band, Date commentDate, boolean wasEdited, String commentContent) {
        this(user, commentDate, wasEdited, commentContent);
        this.band = band;
    }

    public Comment(User user, Artist artist, Date commentDate, boolean wasEdited, String commentContent) {
        this(user, commentDate, wasEdited, commentContent);
        this.artist = artist;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
