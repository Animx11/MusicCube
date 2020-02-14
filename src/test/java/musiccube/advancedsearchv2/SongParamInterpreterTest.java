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


        String excepted = "SELECT s FROM Song s WHERE (LOWER(s.songName) LIKE LOWER(CONCAT('%',:title0,'%')) OR LOWER(s.songName) LIKE LOWER(CONCAT('%',:title1,'%')) ) ";
        assertEquals(excepted,interpreter.getQuery().toString());
        assertEquals("sun",interpreter.getQueryParams().get("title0"));
        assertEquals("rain",interpreter.getQueryParams().get("title1"));
    }
    @Test
    void noBandTest() {
        Map<String,String> map = new HashMap<>();
        map.put("noband","beatles");
        SongParamInterpreter interpreter = new SongParamInterpreter(map);

        String excepted = "SELECT s FROM Song s WHERE (LOWER(s.band.bandName) NOT LIKE LOWER(CONCAT('%',:noband0,'%')))";
        assertEquals(excepted,interpreter.getQuery().toString());
        assertEquals("beatles",interpreter.getQueryParams().get("noband0"));
    }
}