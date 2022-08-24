package problem_d4_3124;

import java.io.*;
import java.util.*;

public class Problem_D4_3124_Sol1 {
	private static class Edge implements Comparable<Edge> {
		private int tail;
		private int head;
		private long weight;
		
		public Edge(int tail, int head, long weight) {
			this.tail = tail;
			this.head = head;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return (int) (this.weight - o.weight);
		}
	}
	
	private static class DisjointSet {
		private int[] parents;
		private int[] sizes;
		
		public DisjointSet(int len) {
			parents = new int[len];
			sizes = new int[len];
			
			for (int i = 0; i < len; i++) {
				parents[i] = i;
				sizes[i] = 1;
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
			
			int parent = rootOfA;
			int child = rootOfB;
			
			if (sizes[parent] < sizes[child]) {
				int tmp = parent;
				parent = child;
				child = tmp;
			}
			
			sizes[parent] += sizes[child];
			parents[child] = parent;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<Edge> edges = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(input.readLine());
				int tail = Integer.parseInt(st.nextToken()) - 1;
				int head = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(tail, head, weight));
			}	
			
			answer.append(String.format("#%d %d%n", tc, kruskal(V, edges)));
		}
		
		System.out.println(answer);
	}
	
	private static long kruskal(int vertexSize, List<Edge> edges) {
		long sumWeight = 0;
		int cnt = 0;
		DisjointSet set = new DisjointSet(vertexSize);
		
		Collections.sort(edges);
		
		for (Edge cur : edges) {
			int tail = cur.tail;
			int head = cur.head;
			
			if (set.union(tail, head)) {
				sumWeight += cur.weight;
				
				if (++cnt == vertexSize - 1) {
					return sumWeight;
				}
			}
		}
		
		return sumWeight;
	}
}
