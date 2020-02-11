package musiccube.advancedsearch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SongParamInterpreterTest {


    @Test
    void emptyParamMapTest() {
        Map<String,String> titles = new HashMap<>();
        AbstractParamInterpreter interpreter = new SongParamInterpreter(titles);

        String query = interpreter.getQuery();

        String excepted = "SELECT s FROM Song s ";
        assertEquals(excepted,query);
    }
    @Test
    void titleOnlyProcessingTest() {
        Map<String,String> titles = new HashMap<>();
        titles.put("title","sun,rain,clouds");
        AbstractParamInterpreter interpreter = new SongParamInterpreter(titles);

        String query = interpreter.getQuery();

        String excepted = "SELECT s FROM Song s WHERE (LOWER(s.songName) IN( LOWER(sun), LOWER(rain), LOWER(clouds) ) ";
        assertEquals(excepted,query);
    }
    @Test
    void notInTitleOnlyProcessingTest() {

        Map<String,String> titles = new HashMap<>();
        titles.put("notitle","sun,rain,clouds");
        AbstractParamInterpreter interpreter = new SongParamInterpreter(titles);

        String query = interpreter.getQuery();

        String excepted = "SELECT s FROM Song s WHERE (LOWER(s.songName) NOT IN( LOWER(sun), LOWER(rain), LOWER(clouds) ) ";
        assertEquals(excepted,query);
    }
    @Test
    void searchByMaxSongLength() {
        Map<String,String> params = new HashMap<>();
        params.put("maxlength","250");
        AbstractParamInterpreter interpreter = new SongParamInterpreter(params);

        String query = interpreter.getQuery();

        String excepted = "SELECT s FROM Song s WHERE (s.songLengthSeconds <= 250 ) ";
        assertEquals(excepted,query);
    }
    @Test
    void searchByMinSongLength() {
//        Map<String,String> params = new HashMap<>();
//        params.put("minlength","250");
//        AbstractParamInterpreter interpreter = new SongParamInterpreter(params);
//
//        String query = interpreter.getQuery();
//
//        String excepted = "SELECT s FROM Song s WHERE (s.songLengthSeconds >= 250 ) ";
//        assertEquals(excepted,query);
    }
    @Test
    void searchByBands() {
        Map<String,String> params = new HashMap<>();

    }
    @Test
    void searchByGenres() {
        Map<String,String> params = new HashMap<>();

    }
    @Test
    void searchByInstruments() {
        Map<String,String> params = new HashMap<>();

    }
    @Test
    void queryWith4ParametersTest() {
        Map<String,String> params = new HashMap<>();
        params.put("title","sun,rain");
        params.put("notitle","clouds");
        params.put("maxlength","600");
        params.put("minlength","100");
        AbstractParamInterpreter interpreter = new SongParamInterpreter(params);

        String query = interpreter.getQuery();

        String excepted = (new StringBuilder())
                .append("SELECT s FROM Song s WHERE ")
                .append("(LOWER(s.songName) IN( LOWER(sun), LOWER(rain) ) ")
                .append("AND (LOWER(s.songName) NOT IN( LOWER(sun) ) ")
                .append("AND (s.songLengthSeconds < 600 ) ")
                .append("AND (s.songLengthSeconds > 100 ) ")
                .toString();
        assertEquals(excepted,query);
    }

}