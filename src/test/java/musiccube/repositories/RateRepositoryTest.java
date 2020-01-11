package musiccube.repositories;

import static org.junit.jupiter.api.Assertions.*;

import musiccube.dtos.SongRatingDto;
import musiccube.entities.Rate;
import musiccube.entities.Song;
import musiccube.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

@RunWith(SpringRunner.class)
@DataJpaTest
class RateRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private RateRepository rateRepository;

    @Test
    void alwaysTrue() {
        assertTrue(true);
    }

    @Test
    void shouldReturnCorrectSongRatingDto() {
        //given
        Song song = new Song("title",1,10,null,null,null);
        testEntityManager.persist(song);
        User user = new User("user","asdasd","asd@asd.asd",new HashSet<>());
        testEntityManager.persist(user);
        Rate rate = new Rate(user,song,7);
        testEntityManager.persist(rate);
        testEntityManager.flush();

        //when
        SongRatingDto found  = rateRepository.findSongStatistics().iterator().next();

        //then
        assertEquals(found.getSongId(),1);
        assertEquals(found.getAvgRating(),7);
        assertEquals(found.getRatedCount(),1);
    }

}