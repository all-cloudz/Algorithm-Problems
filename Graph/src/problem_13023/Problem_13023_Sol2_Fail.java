package problem_13023;

import java.io.*;
import java.util.*;

// 희소그래프라 시간 초과 발생
public class Problem_13023_Sol2_Fail {
	private static int N;
	private static boolean[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N][N];
		
		while (M-- > 0) {
			st = new StringTokenizer(input.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph[v1][v2] = graph[v2][v1] = true;
		}
		
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			dfs(i, 0, visited);
		}
		
		System.out.println(0);
	}
	
	private static void dfs(int cur, int depth, boolean[] visited) {
		if (depth == 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		visited[cur] = true;
		
		for (int next = 0; next < N; next++) {
			if (graph[cur][next] && !visited[next]) {
				dfs(next, depth + 1, visited);
			}
		}
		
		visited[cur] = false;
	}
}
