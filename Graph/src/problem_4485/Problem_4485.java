package problem_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_4485 {
	
	private static class Vertex implements Comparable<Vertex> {
		private int row;
		private int col;
		private int weight;
		
		public Vertex(int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}
	
	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final int INF = 125 * 125 * 9 + 1;
	
	private static int N;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		int tc = 1;
		while ((N = Integer.parseInt(input.readLine())) != 0) {
			map = new int[N][N];
			
//			for (int i = 0; i < N; i++) {
//				map[i] = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//			}
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(input.readLine());
				
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer.append(String.format("Problem %d: %d%n", tc++, dijkstra()));
		}
		
		System.out.println(answer);
	}
	
	private static int dijkstra() {
		PriorityQueue<Vertex> open = new PriorityQueue<>();
		int[][] dists = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dists[i], INF);
		}
		
		open.offer(new Vertex(0, 0, 0));
		dists[0][0] = map[0][0];
		
		while (!open.isEmpty()) {
			Vertex cur = open.poll();
			
			if (dists[cur.row][cur.col] < cur.weight) {
				continue;
			}
			
			if (cur.row == N - 1 && cur.col == N - 1) {
				break;
			}
			
			for (int[] move : DIRECTIONS) {
				int nextRow = cur.row + move[0];
				int nextCol = cur.col + move[1];
				
				if (!isMovable(nextRow, nextCol)) {
					continue;
				}
				
				int minDist = dists[nextRow][nextCol];
				int newDist = dists[cur.row][cur.col] + map[nextRow][nextCol];
				
				if (minDist > newDist) {
					open.offer(new Vertex(nextRow, nextCol, newDist));
					dists[nextRow][nextCol] = newDist;
				}
			}
		}
		
		return dists[N - 1][N - 1];
	}
	
	private static boolean isMovable(int nextRow, int nextCol) {
		return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
	}
	
}
