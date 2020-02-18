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
        List<Set<Integer>> sets = new ArrayList<>();

        assertTrue(RecommendationsIdListBuilder.build(sets,10).isEmpty());
    }
    @Test
    void shouldReturnEmptyListWhenLimitLE0() {
        List<Set<Integer>> sets = new ArrayList<>();

        assertTrue(RecommendationsIdListBuilder.build(sets,0).isEmpty());
        assertTrue(RecommendationsIdListBuilder.build(sets,-10).isEmpty());
    }
    @Test
    void shouldReturnEmptyListWhenGivenListOfEmptySets() {
        List<Set<Integer>> sets = new ArrayList<>();
        sets.add(new HashSet());
        sets.add(new HashSet());

        assertTrue(RecommendationsIdListBuilder.build(sets,10).isEmpty());
    }
    @Test
    void shouldReturnListOf3Integers() {
        List<Set<Integer>> sets = new ArrayList<>();
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
    @Test
    void shouldReturnCommonIdsOnly() {
        List<Set<Integer>> sets = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(5);
        set1.add(3);
        set1.add(7);

        set2.add(7);
        set2.add(12);
        set2.add(5);
        set2.add(10);

        set3.add(11);
        set3.add(7);
        set3.add(5);

        sets.add(set2);
        sets.add(set3);
        sets.add(set1);

        List result = RecommendationsIdListBuilder.build(sets,2);

        assertEquals(2,result.size());
        assertTrue(result.contains(5));
        assertTrue(result.contains(7));

    }
}