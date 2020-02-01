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
import org.springframework.data.domain.PageRequest;
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
        SongRatingDto found  = rateRepository.findBestRatedSongs(new PageRequest(0,10)).iterator().next();

        //then
        assertEquals(found.getSong().getSongName(),"title");
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
        ArrayList<SongRatingDto> found = (ArrayList<SongRatingDto>) rateRepository.findBestRatedSongs(new PageRequest(0,10));

        //then
        assertEquals(found.size(),0);
    }

    @Test
    void shouldReturnRatedSongsInDescendingOrder() {
        Song song10 = new Song("song10",1,10,null,null,null);
        Song song8 = new Song("song8",1,10,null,null,null);
        Song song6 = new Song("song6",1,10,null,null,null);

        Rate rate2 = new Rate(user,song8,8);
        Rate rate = new Rate(user,song10,10);
        Rate rate1 = new Rate(user,song6,6);

        testEntityManager.persist(song10);
        testEntityManager.persist(song8);
        testEntityManager.persist(song6);
        testEntityManager.persist(rate2);
        testEntityManager.persist(rate);
        testEntityManager.persist(rate1);
        testEntityManager.flush();

        //when
        ArrayList<SongRatingDto> found = (ArrayList<SongRatingDto>) rateRepository.findBestRatedSongs(new PageRequest(0, 3));

        //then
        assertEquals(found.get(0).getSong().getSongName(),"song10");
        assertEquals(found.get(1).getSong().getSongName(),"song8");
        assertEquals(found.get(2).getSong().getSongName(),"song6");

    }

    @Test
    void shouldReturnCorrectSongRatingDtoUsingGeneralizedMethod() {
        //given
        Song song = new Song("title",1,10,null,null,null);
        testEntityManager.persist(song);
        Rate rate = new Rate(user,song,7);
        testEntityManager.persist(rate);
        testEntityManager.flush();

        //when
        SongRatingDto found  = rateRepository.findBestRated("song",new PageRequest(0,10)).iterator().next();

        //then
        assertEquals(found.getSong().getSongName(),"title");
        assertEquals(found.getAvgRating(),7);
        assertEquals(found.getRatedCount(),1);
    }

}