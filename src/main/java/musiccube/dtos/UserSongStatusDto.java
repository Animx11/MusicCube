package musiccube.dtos;

public class UserSongStatusDto {
    private String userName;
    private int songId;
    private boolean listened;
    private boolean toListen;

    public UserSongStatusDto() {}

    public UserSongStatusDto(String userName, int albumId, boolean listened, boolean toListen) {
        this.userName = userName;
        this.songId = albumId;
        this.listened = listened;
        this.toListen = toListen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
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
