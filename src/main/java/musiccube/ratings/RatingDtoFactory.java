package musiccube.ratings;

import musiccube.dtos.RatingDto;
import musiccube.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class RatingDtoFactory {
    @Autowired
    private RateRepository repository;

    public List<RatingDto> getRatingDtos(String tableName, int size) {
        PageRequest request = new PageRequest(0,size);
        if (tableName.equals(RatingConstants.ALBUM)) {
            return repository.findBestRatedAlbums(request);
        }
        if (tableName.equals(RatingConstants.ARTIST)) {
            return repository.findBestRatedArtists(request);
        }
        if (tableName.equals(RatingConstants.BAND)) {
            return repository.findBestRatedBands(request);
        }
        if (tableName.equals(RatingConstants.SONG)) {
            return repository.findBestRatedSongsUpdated(request);
        }
        return new ArrayList<>();
    }
}
