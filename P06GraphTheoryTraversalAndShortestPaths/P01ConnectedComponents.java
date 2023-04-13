package P06GraphTheoryTraversalAndShortestPaths;

import java.util.*;
import java.util.stream.Collectors;

public class P01ConnectedComponents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfNodes = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int node = 0; node < numOfNodes; node++) {
            String nextLine = scanner.nextLine();

            if (nextLine.trim().equals("")) {
                graph.add(new ArrayList<>());
            } else {
                List<Integer> nextNodes = Arrays.stream(nextLine
                                .split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                graph.add(nextNodes);
            }
        }

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);

        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.print("Connected component: ");
            for (int component : connectedComponent) {
                System.out.print(component + " ");
            }
            System.out.println();
        }
    }

    private static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> components = new ArrayList<>();

        boolean[] visited = new boolean[graph.size()];

        for (int start = 0; start < visited.length; start++) {
            if (!visited[start]) {
                components.add(new ArrayDeque<>());
                //               dfs(graph, start, visited, components);
                bfs(graph, start, visited, components);
            }
        }

        return components;
    }

    private static void bfs(List<List<Integer>> graph, int start, boolean[] visited, List<Deque<Integer>> components) {
        Deque<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            components.get(components.size() - 1).offer(node);

            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
    }

    private static void dfs(List<List<Integer>> graph, int start, boolean[] visited, List<Deque<Integer>> connectedComponents) {
        if (!visited[start]) {
            visited[start] = true;

            for (int child : graph.get(start)) {
                dfs(graph, child, visited, connectedComponents);
            }

            connectedComponents.get(connectedComponents.size() - 1).offer(start);
        }
    }
}
