package problem_2104;

import java.io.*;
import java.util.*;

public class Problem_2104 {
	private static class MinTree {
		private int[] tree;
		private int size;

		public MinTree(long[] arr) {
			int heightOfTree = (int) Math.ceil(Math.log(arr.length) / Math.log(2)) + 1;
			this.size = (int) Math.pow(2, heightOfTree);

			this.tree = new int[size];
			init(arr);
		}

		public void init(long[] arr) {
			init(arr, 1, 1, arr.length - 1);
		}

		private int init(long[] arr, int nodeIdx, int left, int right) {
			if (left == right) {
				return tree[nodeIdx] = left;
			}

			int mid = left + right >> 1;
			
			int idx1 = init(arr, nodeIdx * 2, left, mid);
			int idx2 = init(arr, nodeIdx * 2 + 1, mid + 1, right);
			
			if (arr[idx1] < arr[idx2]) {
				return tree[nodeIdx] = idx1;
			}
			
			return tree[nodeIdx] = idx2;
		}

		private int minArg(long[] arr, int left, int right) {
			return minArg(1, 1, arr.length - 1, left, right);
		}

		private int minArg(int nodeIdx, int left, int right, int inf, int sup) {
			if (right < inf || left > sup) {
				return 0;
			}

			if (inf <= left && right <= sup) {
				return tree[nodeIdx];
			}

			int mid = left + right >> 1;
			
			int idx1 = minArg(nodeIdx * 2, left, mid, inf, sup);
			int idx2 = minArg(nodeIdx * 2 + 1, mid + 1, right, inf, sup);
			
			if (arr[idx1] < arr[idx2]) {
				return idx1;
			}
			
			return idx2;
		}
	}

	private static class SegmentTree {
		private long[] tree;
		private int size;

		public SegmentTree(long[] arr) {
			int heightOfTree = (int) Math.ceil(Math.log(arr.length) / Math.log(2)) + 1;
			this.size = (int) Math.pow(2, heightOfTree);

			this.tree = new long[size];
			init(arr);
		}

		public void init(long[] arr) {
			init(arr, 1, 1, arr.length - 1);
		}

		private long init(long[] arr, int nodeIdx, int left, int right) {
			if (left == right) {
				return tree[nodeIdx] = arr[left];
			}

			int mid = left + right >> 1;
			return tree[nodeIdx] = init(arr, nodeIdx * 2, left, mid) + init(arr, nodeIdx * 2 + 1, mid + 1, right);
		}

		public long sum(long[] arr, int left, int right) {
			return sum(1, 1, arr.length - 1, left, right);
		}

		private long sum(int nodeIdx, int left, int right, int inf, int sup) {
			if (right < inf || left > sup) {
				return 0;
			}

			if (inf <= left && right <= sup) {
				return tree[nodeIdx];
			}

			int mid = left + right >> 1;
			return sum(nodeIdx * 2, left, mid, inf, sup) + sum(nodeIdx * 2 + 1, mid + 1, right, inf, sup);
		}
	}

	private static int N;
	private static long[] arr;
	private static SegmentTree segTree;
	private static MinTree minTree;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(input.readLine());
		arr = new long[N + 1];

		StringTokenizer st = new StringTokenizer(input.readLine());
		arr[0] = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		segTree = new SegmentTree(arr);
		minTree = new MinTree(arr);
		
		System.out.println(getMax(0, arr.length - 1));
	}
	
	private static long getMax(int left, int right) {
		int minArg = minTree.minArg(arr, left, right);
		long score = getScore(left, right);
		
		if (left == right) {
			return score;
		}
		
		if (left < minArg) {
			score = Math.max(score, getMax(left, minArg - 1));
		}
		
		if (minArg < right) {
			score = Math.max(score, getMax(minArg + 1, right));
		}
		
		return score;
	}
	
	private static long getScore(int left, int right) {
		return segTree.sum(arr, left, right) * arr[minTree.minArg(arr, left, right)];
	}
}
