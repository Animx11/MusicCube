package musiccube.rankings;

import musiccube.dtos.RankingDto;
import musiccube.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RankingDtoFactory {
    @Autowired
    private RateRepository repository;

    public List<RankingDto> getRatingDtos(String tableName, int size) {
        PageRequest request = new PageRequest(0,size);
        if (tableName.equals(RankingConstants.ALBUM)) {
            return repository.findBestRatedAlbums(request);
        }
        if (tableName.equals(RankingConstants.ARTIST)) {
            return repository.findBestRatedArtists(request);
        }
        if (tableName.equals(RankingConstants.BAND)) {
            return repository.findBestRatedBands(request);
        }
        if (tableName.equals(RankingConstants.SONG)) {
            return repository.findBestRatedSongsUpdated(request);
        }
        return new ArrayList<>();
    }
}
