package problem_d4_3124;

import java.io.*;
import java.util.*;

public class Problem_D4_3124_Sol2 {
	private static class Vertex {
		private int label;
		private long weight;
		
		public Vertex(int label, long weight) {
			this.label = label;
			this.weight = weight; 
		}
	}
	
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

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<List<Vertex>> graph = new ArrayList<>();
			for (int i = 0; i < V; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(input.readLine());
				int tail = Integer.parseInt(st.nextToken()) - 1;
				int head = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());

				graph.get(tail).add(new Vertex(head, weight));
				graph.get(head).add(new Vertex(tail, weight));
			}

			answer.append(String.format("#%d %d%n", tc, prim(graph)));
		}

		System.out.println(answer);
	}
	
	private static long prim(List<List<Vertex>> graph) {
		int sumWeight = 0;
		int cnt = 0;
		
		Queue<Vertex> vertices = new ArrayDeque<>();
		boolean[] visited = new boolean[graph.size()];
		PriorityQueue<Edge> open = new PriorityQueue<>();
		
		vertices.offer(new Vertex(0, 0));
		while (!vertices.isEmpty()) {
			Vertex cur = vertices.poll();
			visited[cur.label] = true;
			
			for (Vertex next : graph.get(cur.label)) {
				if (!visited[next.label]) {
					open.offer(new Edge(cur.label, next.label, next.weight));
				}
			}
			
			while (!open.isEmpty()) {
				Edge edge = open.poll();
				
				if (!visited[edge.head]) {
					vertices.offer(new Vertex(edge.head, 0));
					sumWeight += edge.weight;
					cnt++;
					break;
				}
			}
		}
		
		return sumWeight;
	}
}
