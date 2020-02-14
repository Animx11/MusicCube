package musiccube.advancedsearchv2;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NameInQueryBuilderTest {
    @Test
    void queryBuildingTest() {
        StringBuilder builder = new StringBuilder();
        HashMap map = new HashMap();
        NameInQueryBuilder.build(builder,map,"sun,clouds","title","s.songName");

        assertEquals("s.songName LIKE :title0 OR s.songName LIKE :title1 ",builder.toString());
        assertEquals(map.get("title0"),"sun");
        assertEquals(map.get("title1"),"clouds");
    }
}