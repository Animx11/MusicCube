package musiccube.dtos;

public class SongRatingDto {
    private int songId;
    private double avgRating;
    private int ratedCount;

    public SongRatingDto(int songId, double avgRating, int ratedCount) {
        this.songId = songId;
        this.avgRating = avgRating;
        this.ratedCount = ratedCount;
    }

    public int getSongId() {
        return songId;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public int getRatedCount() {
        return ratedCount;
    }
}
