package problem_17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_17472 {
	
	private static class DisjointSet {
		private int[] trees;
		
		public DisjointSet(int cnt) {
			trees = new int[cnt + 1];
			
			for (int i = 2; i <= cnt; i++) {
				trees[i] = i;
			}
		}
		
		public int find(int cur) {
			if (trees[cur] == cur) {
				return cur;
			}
			
			return trees[cur] = find(trees[cur]);
		}
		
		public boolean union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);
			
			if (rootA == rootB) {
				return false;
			}
			
			trees[rootB] = rootA;
			return true;
		}
	}
	
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
	
	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	private static int N, M;
	private static int[][] map;
	private static DisjointSet disjointSet;
	private static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		int cnt = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					setIsland(i, j, ++cnt);
				}
			}
		}
		
		disjointSet = new DisjointSet(cnt);
		edgeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					setEdgeList(i, j, map[i][j]);
				}
			}
		}
		
		System.out.println(kruskal(cnt));
	}
	
	private static void setIsland(int row, int col, int label) {
		Queue<int[]> land = new ArrayDeque<>();
		boolean[][] discovered = new boolean[N][M];
		
		land.offer(new int[] { row, col });
		discovered[row][col] = true;
		
		while (!land.isEmpty()) {
			int[] cur = land.poll();
			map[cur[0]][cur[1]] = label;
			
			for (int[] move : DIRECTIONS) {
				int nextRow = cur[0] + move[0];
				int nextCol = cur[1] + move[1];
				
				if (!isMovable(nextRow, nextCol) || discovered[nextRow][nextCol] || map[nextRow][nextCol] == 0) {
					continue;
				}
				
				if (map[nextRow][nextCol] == 1) {
					land.offer(new int[] { nextRow, nextCol });
					discovered[nextRow][nextCol] = true;
				}
			}
		}
	}
	
	private static void setEdgeList(int row, int col, int label) {
		for (int[] move : DIRECTIONS) {
			int nextRow = row;
			int nextCol = col;
			int dist = 0;
			
			while (true) {
				nextRow += move[0];
				nextCol += move[1];
				
				if (!isMovable(nextRow, nextCol) || map[nextRow][nextCol] == label) {
					break;
				}
				
				if (map[nextRow][nextCol] != 0 && dist < 2) {
					break;
				}
				
				if (map[nextRow][nextCol] != 0) {
					edgeList.add(new Edge(label, map[nextRow][nextCol], dist));
					break;
				}
				
				dist++;
			}
		}
	}
	
	private static boolean isMovable(int nextRow, int nextCol) {
		return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
	}
	
	private static int kruskal(int V) {
		Collections.sort(edgeList);
		
		int minCost = 0;
		int cntOfMST = 0;
		
		for (Edge cur : edgeList) {
			if (cntOfMST == V - 2) {
				return minCost;
			}
			
			if (disjointSet.union(cur.from, cur.to)) {
				minCost += cur.weight;
				cntOfMST++;
			}
		}
		
		return -1;
	}
	
}
