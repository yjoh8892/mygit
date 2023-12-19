// Yoo Jin Oh
// Nov 2 2022 

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WeightedScheduling {
	private static class Job {
		int start;
		int end;
		int weight;

		Job(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	private static int solve(Job[] jobs) {
		int n = jobs.length;
		int[] P = new int[n + 1];
		for (int i = 1; i<= n; i++) {
			int curr = 0;
			while ((curr+1 < i) && (jobs[curr].end <= jobs[i-1].start)) {
				curr++;
			}
			P[i] = curr;
		}
		int[] A = new int[n + 1];
		A[0] = 0;
		for (int i = 1; i<=n; i++) {
			A[i] = Math.max(A[i-1], jobs[i-1].weight + A[P[i]]);
		}
		return A[n];
	}

	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			int N = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i<N; i++) {
				int h = 0;
				int k = Integer.parseInt(scanner.nextLine());
				Job[] jobs = new Job[k];
				for (int j = 0; j<k; j++) {
					String[] parts = scanner.nextLine().trim().split("\\s+");
					jobs[j] = new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
				}
				Arrays.sort(jobs, Comparator.comparingInt(o -> o.end));
				System.out.println(solve(jobs));
			}
		}
	}
}
