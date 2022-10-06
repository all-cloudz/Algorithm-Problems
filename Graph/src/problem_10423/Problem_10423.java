package problem_10423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Problem_10423 {
	
	private static class Edge implements Comparable<Edge> {

		private int from;
		private int to;
		private int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}
	
	private static class DisjointSet {
		private int[] trees;
		
		public DisjointSet(int N) {
			trees = new int[N + 1];
			
			for (int i = 1; i <= N; i++) {
				trees[i] = i;
			}
		}
		
		public int find(int a) {
			if (trees[a] == a) {
				return a;
			}
			
			return trees[a] = find(trees[a]);
		}
		
		public boolean union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);
			
			if (rootA == rootB) {
				return false;
			}
			
			if (plants.contains(rootA) && plants.contains(rootB)) {
				return false;
			}
			
			if (plants.contains(rootA)) {
				trees[rootB] = rootA;
			} else {
				trees[rootA] = rootB;
			}
			
			return true;
		}
		
		private boolean isPlantedAll() {
			for (int node : trees) {
				if (!plants.contains(find(node))) {
					return false;
				}
			}
			
			return true;
		}
	}
	
	private static int N;
	private static DisjointSet disjointSet;
	
	private static int M;
	private static Edge[] edges;
	
	private static int K;
	private static Set<Integer> plants;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		
		disjointSet = new DisjointSet(N);
		edges = new Edge[M];
//		plants = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
		plants = new HashSet<>();
		
		tokenizer = new StringTokenizer(input.readLine());
		for (int i = 0; i < K; i++) {
			plants.add(Integer.parseInt(tokenizer.nextToken()));
		}
		
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			int weight = Integer.parseInt(tokenizer.nextToken());
			
			edges[i] = new Edge(from, to, weight);
		}
		
		System.out.println(kruskal());
	}
	
	private static int kruskal() {
		int sizeOfMST = 0;
		int weightOfMST = 0;
		
		Arrays.sort(edges);
		for (Edge edge : edges) {
			if (sizeOfMST == N - K) {
				break;
			}
			
			if (disjointSet.union(edge.from, edge.to)) {
				weightOfMST += edge.weight;
			}
		}
		
		return weightOfMST;
	}

}
