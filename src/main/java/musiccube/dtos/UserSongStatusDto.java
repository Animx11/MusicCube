package musiccube.dtos;

public class UserSongStatusDto {
    private String userName;
    private int albumId;
    private boolean listened;
    private boolean toListen;

    public UserSongStatusDto() {}

    public UserSongStatusDto(String userName, int albumId, boolean listened, boolean toListen) {
        this.userName = userName;
        this.albumId = albumId;
        this.listened = listened;
        this.toListen = toListen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
