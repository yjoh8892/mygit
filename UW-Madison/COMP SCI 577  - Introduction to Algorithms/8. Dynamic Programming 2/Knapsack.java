// Yoo Jin Oh
// Nov 3 2022

import java.io.IOException;
import java.util.*;

public class Knapsack {

	private static int solve(int n, int w, int[] ws, int[] vs) {
		int[][] A = new int[n+1][w+1];
		for (int i = 0; i<= n; i++) {
			A[i][0] = 0;
		}
		for (int i = 0; i<= n; i++) {
			A[0][i] = 0;
		}

		for (int i = 1; i<=n; i++) {
			for (int j = 1; j<=w; j++) {
				int best = A[i-1][j];
				int currW = ws[i-1];
				if (currW > j) {
					A[i][j] = best;
				}
				else {
					A[i][j] = Math.max(best, A[i-1][j-currW] + vs[i-1]);
				}
			}
		}
		return A[n][w];
	}

	public static void main(String[] args) throws IOException {
		try(Scanner scanner = new Scanner(System.in)) {
			int k = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i<k; i++) {
				String[] parts = scanner.nextLine().trim().split("\\s+");
				int n = Integer.parseInt(parts[0]);
				int w = Integer.parseInt(parts[1]);
				int[] ws = new int[n];
				int[] vs = new int[n];
				for (int j = 0; j<n; j++) {
					parts = scanner.nextLine().trim().split("\\s+");
					ws[j] = Integer.parseInt(parts[0]);
					vs[j] = Integer.parseInt(parts[1]);
				}
				System.out.println(solve(n, w, ws, vs));
			}
		}
	}
}
