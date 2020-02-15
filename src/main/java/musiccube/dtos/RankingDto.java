package musiccube.dtos;

public class RankingDto {
    private Object rated;
    private double avgRating;
    private long ratedCount;

    public RankingDto(Object rated, double avgRating, long ratedCount) {
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