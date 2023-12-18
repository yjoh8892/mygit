// Yoo Jin Oh
// Oct 12 2022

import java.util.*;

public class Inversions {
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			int n = Integer.parseInt(scanner.nextLine().trim());
			for (int i = 0; i<n; i++) {
				int k = Integer.parseInt(scanner.nextLine().trim());
				int[] A = new int[k];
				int[] B = new int[k];
				for (int j = 0; j<k; j++) {
					A[j] = Integer.parseInt(scanner.nextLine().trim());
				}
				for (int j = 0; j<k; j++) {
					B[j] = Integer.parseInt(scanner.nextLine().trim());
				}


				List<Pair> list = new ArrayList<>();
				for (int j = 0; j<k; j++) {
					list.add(new Pair(A[j], B[j]));
				}
				// O(n log n)
				list.sort(Comparator.comparingInt(o -> o.a));

				int[] c = new int[k];
				for (int j = 0; j<k; j++) {
					c[j] = list.get(j).b;
				}

				// O(n log n)
				long inversions = inv(c);
				System.out.println(inversions);
			}
		}
	}

	public static long inv(int[] array) {
		if (array.length < 2)
			return 0;

		int m = (array.length + 1) / 2;
		int[] left = Arrays.copyOfRange(array, 0, m);
		int[] right = Arrays.copyOfRange(array, m, array.length);

		return inv(left) + inv(right) + merge(array, left, right);
	}

	private static long merge(int[] arr, int[] left, int[] right) {
		int i = 0, j = 0;
		long count = 0;
		while (i < left.length || j < right.length) {
			if (i == left.length) {
				arr[i+j] = right[j];
				j++;
			} else if (j == right.length) {
				arr[i+j] = left[i];
				i++;
			} else if (left[i] <= right[j]) {
				arr[i+j] = left[i];
				i++;
			} else {
				arr[i+j] = right[j];
				count += left.length-i;
				j++;
			}
		}
		return count;
	}

	private static class Pair {
		int a;
		int b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
