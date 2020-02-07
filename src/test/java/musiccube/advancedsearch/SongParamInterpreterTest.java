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

}