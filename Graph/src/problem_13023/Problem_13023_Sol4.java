package problem_13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_13023_Sol4 {
	private static class Vertex {
		private int to;
		private Vertex next;
		
		public Vertex(int to, Vertex next) {
			this.to = to;
			this.next = next;
		}
	}
	
	private static int N;
	private static Vertex[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new Vertex[N];
		
		while (M-- > 0) {
			st = new StringTokenizer(input.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph[v1] = new Vertex(v2, graph[v1]);
			graph[v2] = new Vertex(v1, graph[v2]);
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
		
		Vertex next = graph[cur];
		while (next != null) {
			if (!visited[next.to]) {
				dfs(next.to, depth + 1, visited);
			}
			
			next = next.next;
		}
		
		visited[cur] = false;
	}
}
