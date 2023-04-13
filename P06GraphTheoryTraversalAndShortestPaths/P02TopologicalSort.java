package P06GraphTheoryTraversalAndShortestPaths;

import java.util.*;
import java.util.stream.Collectors;

public class P02TopologicalSort {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", new ArrayList<>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<>(Arrays.asList("D", "E")));
        graph.put("C", new ArrayList<>(Arrays.asList("F")));
        graph.put("D", new ArrayList<>(Arrays.asList("C", "F")));
        graph.put("E", new ArrayList<>(Arrays.asList("D")));
        graph.put("F", new ArrayList<>(Arrays.asList()));

        Collection<String> topSorter = topSort(graph);

        for (String node : topSorter) {
            System.out.print(node + " ");
        }
    }


    public static List<String> topSort(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesCount = getDependenciesCount(graph);

        List<String> sorted = new ArrayList<>();

        while (!graph.isEmpty()) {
            String key = graph.keySet()
                    .stream()
                    .filter(k -> dependenciesCount.get(k)==0)
                    .findFirst()
                    .orElse(null);

            if (key == null) {
                break;
            }

            for (String child : graph.get(key)) {
                dependenciesCount.put(child, dependenciesCount.get(child) - 1);
            }

            sorted.add(key);
            graph.remove(key);
        }

        if (!graph.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return sorted;
    }

    public static Map<String, Integer> getDependenciesCount(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesCount = new LinkedHashMap<>();

        for (Map.Entry <String, List<String>> node : graph.entrySet()) {
            dependenciesCount.putIfAbsent(node.getKey(), 0);

            for (String child : node.getValue()) {
                dependenciesCount.putIfAbsent(child, 0);
                dependenciesCount.put(child, dependenciesCount.get(child) + 1);
            }
        }

        return dependenciesCount;
    }


}


