// Yoo Jin Oh
// Nov 17 2022

import java.util.*;

public class MaxMatching {
	private static int ff(Edge[][] graph) {
		int source = 0;
		int target = graph.length - 1;
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
				graph[e.from][e.to].capacity -= min;
				graph[e.to][e.from].capacity += min;
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

		for (int i = 0; i <= target; i++) {
			if (!visited.contains(i) && graph[curr][i] != null && graph[curr][i].capacity > 0) {
				visited.add(i);
				path.add(new Edge(curr, i, graph[curr][i].capacity));
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
				int m = Integer.parseInt(parts[0]);
				int n = Integer.parseInt(parts[1]);
				int q = Integer.parseInt(parts[2]);
				Edge[][] graph = new Edge[m+n+2][m+n+2];
				for (int j = 0; j < q; j++) {
					parts = scanner.nextLine().trim().split("\\s+");
					int from = Integer.parseInt(parts[0].trim());
					int to = Integer.parseInt(parts[1].trim());
					graph[from][to + m] = new Edge(from, to + m, 1);
					graph[to + m][from] = new Edge(to + m,from,  0);
				}
				for (int j = 1; j<=m; j++) {
					graph[0][j] = new Edge(0, j, 1);
					graph[j][0] = new Edge(j, 0, 0);
				}
				for (int j = m+1; j<=n+m; j++) {
					graph[j][n+m+1] = new Edge(j, n+m+1, 1);
					graph[n+m+1][j] = new Edge(n+m+1, j, 0);
				}
				int max = ff(graph);
				boolean isPerfect = (max == m) && (m == n);
				System.out.println(max + " " + (isPerfect ? "Y" : "N"));
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
