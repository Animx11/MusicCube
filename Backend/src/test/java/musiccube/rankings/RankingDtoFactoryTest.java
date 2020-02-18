package musiccube.rankings;

import musiccube.dtos.RankingDto;
import musiccube.entities.Album;
import musiccube.entities.Rate;
import musiccube.entities.User;
import musiccube.repositories.AlbumRepository;
import musiccube.repositories.RateRepository;
import musiccube.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RankingDtoFactoryTest {
    @Mock
    private RateRepository rateRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AlbumRepository albumRepository;
    @InjectMocks
    private RankingDtoFactory factory = new RankingDtoFactory();

    @Test
    @Disabled("Mocki nie działają? Sprawdzić przez integrację")
    void shouldReturnSortedAlbumDtos() {
        User user = new User("user","asdasd","asd@asd.asd",new HashSet<>());
        userRepository.save(user);
        Album album1 = new Album();
        Album album2 = new Album();
        Album album3 = new Album();
        album1.setAlbumName("album1");
        album2.setAlbumName("album2");
        album3.setAlbumName("album3");
        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);
        Rate rate1 = new Rate(user,album1,5);
        Rate rate2 = new Rate(user,album2,10);
        Rate rate3 = new Rate(user,album3,7);
        rateRepository.save(rate1);
        rateRepository.save(rate2);
        rateRepository.save(rate3);

        List<RankingDto> result = factory.getRatingDtos(RankingConstants.ALBUM,5);
        assertEquals("album2",((Album)result.get(0).getRated()).getAlbumName());
        assertEquals("album3",((Album)result.get(1).getRated()).getAlbumName());
        assertEquals("album1",((Album)result.get(2).getRated()).getAlbumName());
    }
}