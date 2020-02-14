package musiccube.advancedsearchv2;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SongParamInterpreterTest {

    @Test
    void searchByTitlesTest() {
        Map<String, String> params = new HashMap<>();
        params.put("title","sun,rain");
        SongParamInterpreter interpreter = new SongParamInterpreter(params);


        String excepted = "SELECT s FROM Song s WHERE (s.songName LIKE :title0 OR s.songName LIKE :title1 ) ";
        assertEquals(excepted,interpreter.getQuery().toString());
        assertEquals("sun",interpreter.getQueryParams().get("title0"));
        assertEquals("rain",interpreter.getQueryParams().get("title1"));
    }
}