package musiccube.recommendations;

import java.util.*;

public class RecommendationsIdListBuilder {
    private RecommendationsIdListBuilder() {}


    public static List<Integer> build(List<Set<Integer>> idSets, int limit) {
        final HashMap<Integer,Integer> idsAndWeightsMap = new HashMap<>();
        idSets.forEach(set -> set.forEach(id -> {
            if (idsAndWeightsMap.containsKey(id)) {
                idsAndWeightsMap.replace(id,idsAndWeightsMap.get(id) + 1);
            } else {
                idsAndWeightsMap.put(id,1);
            }
        }));
        return new ArrayList<>(sortByValue(idsAndWeightsMap).keySet()).subList(0,limit);
    }

    private static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm)
    {
        // Create a list from elements of HashMap 
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<>(hm.entrySet());

        // Sort the list 
        list.sort(Comparator.comparing(o -> (o.getValue())));

        // put data from sorted list to hashmap  
        HashMap<Integer, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
