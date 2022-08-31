package problem_2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_2042 {
	
	private static class SumTree {
		private long[] tree;
		
		public SumTree(long[] nums) {
			int heightOfTree = (int) Math.ceil(Math.log(nums.length) / Math.log(2));
			this.tree = new long[(int) Math.pow(2, heightOfTree + 1)];
			
			init(1, nums, 1, nums.length - 1);
		}
		
		public long init(int node, long[] nums, int start, int end) {
			if (start == end) {
				return tree[node] = nums[start];
			}
			
			int mid = start + (end - start >> 1);
			return tree[node] = init(2 * node, nums, start, mid) + init(2 * node + 1, nums, mid + 1, end);
		}
		
		public long sum(int node, int start, int end, int left, int right) {
			if (end < left || right < start) {
				return 0;
			}
			
			if (left <= start && end <= right) {
				return tree[node];
			}
			
			int mid = start + (end - start >> 1);
			return sum(2 * node, start, mid, left, right) + sum(2 * node + 1, mid + 1, end, left, right);
		}
		
		public long update(int node, int start, int end, int idx, long value) {
			if (idx < start || end < idx) {
				return tree[node];
			}

			if (start == idx && idx == end) {
				return tree[node] = value;
			}

			int mid = start + (end - start >> 1);
			return tree[node] = update(2 * node, start, mid, idx, value) + update(2 * node + 1, mid + 1, end, idx, value);
		}

//		public void update(int node, long start, long end, long idx, long diff) {
//			if (idx < start || end < idx) {
//				return;
//			}
//
//			tree[node] += diff;
//
//			if (start < end) {
//				long mid = start + (end - start >> 1);
//				update(2 * node, start, mid, idx, diff);
//				update(2 * node + 1, mid + 1, end, idx, diff);
//			}
//		}
	}
	
	private static int N;
	private static long[] nums;
	private static int M, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		
		nums = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(input.readLine());
		}
		
		SumTree sumTree = new SumTree(nums);
		for (long size = M + K, i = 0; i < size; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			long c = Long.parseLong(tokenizer.nextToken());
			
			if (a == 1) {
				sumTree.update(1, 1, N, b, c);
//				sumTree.update(1, 1, N, b, c - nums[b]);
//				nums[b] = c;
				continue;
			}
			
			answer.append(sumTree.sum(1, 1, N, b, (int) c)).append('\n');
		}
		
		System.out.print(answer);
	}
	
}
