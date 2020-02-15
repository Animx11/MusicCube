package musiccube.recommendations;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationsIdListBuilderTest {
    @Test
    void shouldReturnEmptyListWhenEmptyInputSet() {
        List<Set> sets = new ArrayList<>();

        assertTrue(RecommendationsIdListBuilder.build(sets,10).isEmpty());
    }
    @Test
    void shouldReturnEmptyListWhenLimitLE0() {
        List<Set> sets = new ArrayList<>();

        assertTrue(RecommendationsIdListBuilder.build(sets,0).isEmpty());
        assertTrue(RecommendationsIdListBuilder.build(sets,-10).isEmpty());
    }
}