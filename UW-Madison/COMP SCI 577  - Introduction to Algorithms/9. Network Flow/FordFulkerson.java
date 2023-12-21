// Yoo Jin Oh
// Nov 10 2022

import java.util.*;

public class FordFulkerson {
	private static int ff(Edge[][] graph) {
		int source = 1;
		int target = graph.length;
		int flow = 0;
		List<Edge> path = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		visited.add(source);
		while (dfs(graph, source, target, path, visited)) {
			int min = Integer.MAX_VALUE;
			for (Edge e : path) {
				if (e.capacity < min) {
					min = e.capacity;
				}
			}

			flow += min;
			for (Edge e : path) {
				graph[e.from - 1][e.to - 1].capacity -= min;
				graph[e.to - 1][e.from - 1].capacity += min;
			}

			path.clear();
			visited.clear();
			visited.add(source);
		}
		return flow;
	}

	private static boolean dfs(Edge[][] graph, int curr, int target, List<Edge> path, Set<Integer> visited) {
		if (curr == target) {
			return true;
		}

		for (int i = 1; i <= target; i++) {
			if (!visited.contains(i) && graph[curr - 1][i - 1] != null && graph[curr - 1][i - 1].capacity > 0) {
				visited.add(i);
				path.add(new Edge(curr, i, graph[curr - 1][i - 1].capacity));
				boolean result = dfs(graph, i, target, path, visited);
				if (result) {
					return true;
				}
				path.remove(path.size() - 1);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			int N = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i < N; i++) {
				String[] parts = scanner.nextLine().trim().split("\\s+");
				int n = Integer.parseInt(parts[0]);
				int k = Integer.parseInt(parts[1]);
				Edge[][] graph = new Edge[n][n];
				for (int j = 0; j < k; j++) {
					parts = scanner.nextLine().trim().split("\\s+");
					int from = Integer.parseInt(parts[0].trim());
					int to = Integer.parseInt(parts[1].trim());
					int capacity = Integer.parseInt(parts[2].trim());
					if (graph[from - 1][to - 1] == null) {
						graph[from - 1][to - 1] = new Edge(from, to, capacity);
						graph[to - 1][from - 1] = new Edge(from, to, capacity);
					} else {
						graph[from - 1][to - 1].capacity += capacity;
						graph[to - 1][from - 1].capacity += capacity;
					}
				}

				System.out.println(ff(graph));
			}
		}
	}

	private static class Edge {
		private final int from;
		private final int to;
		private int capacity;

		public Edge(int from, int to, int capacity) {
			this.from = from;
			this.to = to;
			this.capacity = capacity;
		}
	}
}
