package musiccube.ratings;

import musiccube.dtos.RatingDto;
import musiccube.entities.Album;
import musiccube.entities.Rate;
import musiccube.entities.User;
import musiccube.repositories.RateRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class RatingDtoFactoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private RateRepository rateRepository;
    private RatingDtoFactory factory = new RatingDtoFactory();

    @Test
    void shouldReturnSortedAlbumDtos() {
        User user = new User("user","asdasd","asd@asd.asd",new HashSet<>());
        Album album1 = new Album();
        Album album2 = new Album();
        Album album3 = new Album();
        album1.setAlbumName("album1");
        album2.setAlbumName("album2");
        album3.setAlbumName("album3");
        testEntityManager.persist(user);
        testEntityManager.persist(album1);
        testEntityManager.persist(album2);
        testEntityManager.persist(album3);
        Rate rate1 = new Rate(user,album1,5);
        Rate rate2 = new Rate(user,album2,10);
        Rate rate3 = new Rate(user,album3,7);
        testEntityManager.persist(rate1);
        testEntityManager.persist(rate2);
        testEntityManager.persist(rate3);

        List<RatingDto> result = factory.getRatingDtos(RatingConstants.ALBUM,5);
        assertEquals("album2",((Album)result.get(0).getRated()).getAlbumName());
        assertEquals("album3",((Album)result.get(1).getRated()).getAlbumName());
        assertEquals("album1",((Album)result.get(2).getRated()).getAlbumName());
    }
}