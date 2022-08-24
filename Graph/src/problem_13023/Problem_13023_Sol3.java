package problem_13023;

import java.io.*;
import java.util.*;

public class Problem_13023_Sol3 {
	private static int N;
	private static Map<Integer, List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new HashMap<>();

		while (M-- > 0) {
			st = new StringTokenizer(input.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph.putIfAbsent(v1, new ArrayList<>());
			graph.get(v1).add(v2);
			
			graph.putIfAbsent(v2, new ArrayList<>());
			graph.get(v2).add(v1);
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
		
		if (!graph.containsKey(cur)) {
			return;
		}

		for (int next : graph.get(cur)) {
			if (!visited[next]) {
				dfs(next, depth + 1, visited);
			}
		}

		visited[cur] = false;
	}
}
