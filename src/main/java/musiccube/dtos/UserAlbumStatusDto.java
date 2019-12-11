package musiccube.dtos;

public class UserAlbumStatusDto {
    private String userName;
    private int albumId;
    private boolean owned;
    private boolean sought;

    public UserAlbumStatusDto() {}
    public UserAlbumStatusDto(String userName, int albumId, boolean owned, boolean sought) {
        this.userName = userName;
        this.albumId = albumId;
        this.owned = owned;
        this.sought = sought;
    }

    public String getUserName() {
        return userName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public boolean isOwned() {
        return owned;
    }

    public boolean isSought() {
        return sought;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public void setSought(boolean sought) {
        this.sought = sought;
    }
}
