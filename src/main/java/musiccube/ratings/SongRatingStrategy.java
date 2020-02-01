package musiccube.ratings;

import musiccube.dtos.RatingDto;
import musiccube.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SongRatingStrategy implements RatingStrategyIf{
    @Autowired
    private RateRepository repository;

    @Override
    public RatingDto getRating() {
        return null;
    }
}
