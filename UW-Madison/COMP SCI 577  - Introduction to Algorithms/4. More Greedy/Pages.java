// Yoo Jin OH
// Oct 6 2022

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pages {
	private static int getFurthest(int[] ids, int from, List<Integer> cache) {
		int bestDist = -1;
		int bestPage = -1;
		for (Integer page : cache) {
			int curr = Integer.MAX_VALUE;
			for (int i = from; i < ids.length; i++) {
				if (ids[i] == page) {
					curr = i - from;
					break;
				}
			}
			if (curr > bestDist) {
				bestDist = curr;
				bestPage = page;
			}
		}
		return bestPage;
	}

	private static int calculateFaults(int size, int[] ids) {
		int faults = 0;
		List<Integer> cache = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			if (!cache.contains(ids[i])) {
				faults++;
				if (cache.size() == size) {
					int furthestPage = getFurthest(ids, i, cache);
					cache.remove(Integer.valueOf(furthestPage));
				}
				cache.add(ids[i]);
			}
		}
		return faults;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
			int N = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i  <N; i++) {
				int size = Integer.parseInt(scanner.nextLine().trim());
				int len = Integer.parseInt(scanner.nextLine().trim());
				int[] ids = new int[len];
				String[] parts = scanner.nextLine().split("\\s+");
				for (int j = 0; j < len; j++) {
					ids[j] = Integer.parseInt(parts[j]);
				}
				System.out.println(calculateFaults(size, ids));
			}
	}
}
