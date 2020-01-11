package musiccube.dtos;

public class SongRatingDto {
    private int songId;
    private String songName;
    private double avgRating;
    private long ratedCount;

    public SongRatingDto(int songId, String songName, double avgRating, long ratedCount) {
        this.songId = songId;
        this.songName = songName;
        this.avgRating = avgRating;
        this.ratedCount = ratedCount;
    }

    public String getSongName() {
        return songName;
    }

    public int getSongId() {
        return songId;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public long getRatedCount() {
        return ratedCount;
    }
}
