package musiccube.dtos;

public class RatingDto {
    private Object rated;
    private double avgRating;
    private long ratedCount;

    public RatingDto(Object rated, double avgRating, long ratedCount) {
        this.rated = rated;
        this.avgRating = avgRating;
        this.ratedCount = ratedCount;
    }

    public Object getRated() {
        return rated;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public long getRatedCount() {
        return ratedCount;
    }
}