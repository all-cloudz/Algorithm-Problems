package problem_d4_7465;

import java.io.*;
import java.util.*;

public class Problem_D4_7465 {
	private static class DisjointSet {
		private int[] parents;
		
		public DisjointSet(int len) {
			parents = new int[len];
			
			for (int i = 0; i < len; i++) {
				parents[i] = i;
			}
		}
		
		public int find(int a) {
			if (parents[a] == a) {
				return a;
			}
			
			return parents[a] = find(parents[a]);
		}
		
		public boolean union(int a, int b) {
			int rootOfA = find(a);
			int rootOfB = find(b);
			
			if (rootOfA == rootOfB) {
				return false;
			}
			
			parents[rootOfB] = rootOfA;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			DisjointSet set = new DisjointSet(N);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(input.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				if (set.union(a, b)) {
					N--;
				}
			}
			
			answer.append(String.format("#%d %d%n", tc, N));
		}
		
		System.out.println(answer);
	}
}
