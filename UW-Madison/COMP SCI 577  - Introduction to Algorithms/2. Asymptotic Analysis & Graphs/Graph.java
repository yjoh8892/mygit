// Yoo Jin OH
// SEP 22 2022

import java.util.*;

public class Graph {
    private final Map<String, LinkedList<String>> graph = new HashMap<>();
    // add node with adjacent nodes to graph
    void addNode(String v, List<String> w) {
        graph.put(v, new LinkedList<>(w));
    }

    // mark node as visited and visit its adjacent nodes
    void visitNode(String v, Map<String, Boolean> visited) {

        // Mark the current node as visited and print it
        visited.put(v, true);
        System.out.print(v + " ");

        // visit its adjacent nodes
        for (String n : graph.get(v)) {
            if (!visited.get(n)) {
                visitNode(n, visited);
            }
        }
    }

    // perform a depth first search
    void depthFirstSearch(String v) {

        // initialize visited map to false for all nodes
        Map<String, Boolean> visited = new HashMap<>();
        for (String c : graph.keySet()) {
            visited.put(c, false);
        }

        // visit the first node
        visitNode(v, visited);

        // visit any node which was unreachable till all nodes are reached
        for (String c : graph.keySet()) {
            if(!visited.get(c)){
                visitNode(c, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // read num of instances
        int numOfInstances = Integer.parseInt(scanner.nextLine());

        // read an instance
        for (int i = 0; i < numOfInstances; i++) {
            int numNodes = Integer.parseInt(scanner.nextLine());

            Graph graph = new Graph();
            String first = null;

            // add all edges to the graph
            for (int j = 0; j < numNodes; j++) {
                String line = scanner.nextLine();
                String[] values = line.split(" ");

                if(first == null) {
                    // store first node to start dfs with
                    first = values[0];
                }

                List<String> adjacentNodes = Arrays.asList(values);
                graph.addNode(values[0], adjacentNodes.subList(1, adjacentNodes.size()));
            }

            // perform DFS from first node
            graph.depthFirstSearch(first);

            System.out.println();
        }
    }
}
