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


        assertEquals("SELECT s FROM Song s WHERE (s.songName LIKE :title0 OR s.songName LIKE :title1 ) ",interpreter.getQuery());
        assertEquals(interpreter.getQueryParams().get("title0"),"sun");
        assertEquals(interpreter.getQueryParams().get("title1"),"clouds");
    }
}