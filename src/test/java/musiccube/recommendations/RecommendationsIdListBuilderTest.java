package musiccube.recommendations;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
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
    @Test
    void shouldReturnEmptyListWhenGivenListOfEmptySets() {
        List<Set> sets = new ArrayList<>();
        sets.add(new HashSet());
        sets.add(new HashSet());

        assertTrue(RecommendationsIdListBuilder.build(sets,10).isEmpty());
    }
    @Test
    void shouldReturnListOf3Integers() {
        List<Set> sets = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        sets.add(set);

        List result = RecommendationsIdListBuilder.build(sets,5);

        assertEquals(3,result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }
}