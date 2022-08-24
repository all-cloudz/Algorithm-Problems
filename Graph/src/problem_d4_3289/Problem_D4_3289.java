package problem_d4_3289;

import java.io.*;
import java.util.*;

public class Problem_D4_3289 {
	private static class DisjointSet {
		private int[] parents;
		
		public DisjointSet(int n) {
			parents = new int[n + 1];
			
			for (int i = 1; i <= n; i++) {
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
			answer.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(input.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			DisjointSet set = new DisjointSet(n);
			while (m-- > 0) {
				st = new StringTokenizer(input.readLine());
				String operator = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (operator.equals("0")) {
					set.union(a, b);
					continue;
				}
				
				answer.append((set.find(a) == set.find(b)) ? 1 : 0);
			}
			
			answer.append('\n');
		}
		
		System.out.println(answer);
	}
}
