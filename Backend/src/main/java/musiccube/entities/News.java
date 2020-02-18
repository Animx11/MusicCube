package musiccube.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class News {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String newsTitle;

    @Column
    private Date newsCreationDate;

    @Column
    private String creator;

    @Column
    private String contentNewsDirFile;

    @Column
    private String imageUrl;

    public News() {
    }

    public News(String newsTitle, Date newsCreationDate, String creator, String contentNewsDirFile) {
        this.newsTitle = newsTitle;
        this.newsCreationDate = newsCreationDate;
        this.creator = creator;
        this.contentNewsDirFile = contentNewsDirFile;
    }

    public News(String newsTitle, Date newsCreationDate, String creator, String contentNewsDirFile, String imageUrl) {
        this.newsTitle = newsTitle;
        this.newsCreationDate = newsCreationDate;
        this.creator = creator;
        this.contentNewsDirFile = contentNewsDirFile;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Date getNewsCreationDate() {
        return newsCreationDate;
    }

    public void setNewsCreationDate(Date newsCreationDate) {
        this.newsCreationDate = newsCreationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContentNewsDirFile() {
        return contentNewsDirFile;
    }

    public void setContentNewsDirFile(String contentNewsDirFile) {
        this.contentNewsDirFile = contentNewsDirFile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
