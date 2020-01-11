package musiccube.repositories;

import static org.junit.jupiter.api.Assertions.*;

import musiccube.dtos.SongRatingDto;
import musiccube.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@DataJpaTest
class RateRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private RateRepository rateRepository;

    private User user;

    @BeforeEach
    void init() {
        user = new User("user","asdasd","asd@asd.asd",new HashSet<>());
        testEntityManager.persist(user);
        testEntityManager.flush();
    }

    @Test
    void shouldReturnCorrectSongRatingDto() {
        //given
        Song song = new Song("title",1,10,null,null,null);
        testEntityManager.persist(song);
        Rate rate = new Rate(user,song,7);
        testEntityManager.persist(rate);
        testEntityManager.flush();

        //when
        SongRatingDto found  = rateRepository.findSongStatistics().iterator().next();

        //then
        assertEquals(found.getSongId(),1);
        assertEquals(found.getSongName(),"title");
        assertEquals(found.getAvgRating(),7);
        assertEquals(found.getRatedCount(),1);
    }

    @Test
    void shouldReturnEmptyListWhenNoSongsWhereRated() {
        //given
        Band band = new Band();
        testEntityManager.persist(band);
        Album album = new Album();
        testEntityManager.persist(album);
        Rate bandRate = new Rate(user,band,10);
        Rate albumRate = new Rate(user, album, 10);
        testEntityManager.persist(bandRate);
        testEntityManager.persist(albumRate);
        testEntityManager.flush();

        //when
        ArrayList<SongRatingDto> found = (ArrayList<SongRatingDto>) rateRepository.findSongStatistics();

        //then
        assertEquals(found.size(),0);
    }

}