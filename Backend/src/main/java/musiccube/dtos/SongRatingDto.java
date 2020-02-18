package musiccube.dtos;

import musiccube.entities.Song;

public class SongRatingDto {
    private Song song;
    private double avgRating;
    private long ratedCount;

    public SongRatingDto(Song song, double avgRating, long ratedCount) {
        this.song = song;
        this.avgRating = avgRating;
        this.ratedCount = ratedCount;
    }

    public Song getSong() {
        return song;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public long getRatedCount() {
        return ratedCount;
    }
}
