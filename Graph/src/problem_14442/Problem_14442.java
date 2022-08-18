package problem_14442;

import java.io.*;
import java.util.*;

public class Problem_14442 {
	private static class Point {
		private int row;
		private int col;
		private int dist;
		private int destroyCnt;
		
		public Point(int row, int col) {
			this(row, col, 1, 0);
		}
		
		public Point(int row, int col, int dist, int destroyCnt) {
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.destroyCnt = destroyCnt;
		}
	}
	
	private static int N;
	private static int M;
	private static int K;
	private static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = input.readLine().toCharArray();
		}
		
		System.out.println(getShortestDistance());
	}
	
	private static int getShortestDistance() {
		final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		
		Queue<Point> points = new ArrayDeque<>();
		boolean[][][] discovered = new boolean[K + 1][N][M];
		
		points.offer(new Point(0, 0));
		discovered[0][0][0] = true;
		
		while (!points.isEmpty()) {
			Point cur = points.poll();
			
			if (cur.row == N - 1 && cur.col == M - 1) {
				return cur.dist;
			}
			
			for (int[] move : DIRECTIONS) {
				int nextRow = cur.row + move[0];
				int nextCol = cur.col + move[1];
				
				if (!isMovable(nextRow, nextCol)) {
					continue;
				}
				
				boolean isWall = (map[nextRow][nextCol] == '1') ? true : false;
				
				if (!isWall && !discovered[cur.destroyCnt][nextRow][nextCol]) {
					points.offer(new Point(nextRow, nextCol, cur.dist + 1, cur.destroyCnt));
					discovered[cur.destroyCnt][nextRow][nextCol] = true;
					continue;
				}
				
				if (isWall && cur.destroyCnt < K && !discovered[cur.destroyCnt + 1][nextRow][nextCol]) {
					points.offer(new Point(nextRow, nextCol, cur.dist + 1, cur.destroyCnt + 1));
					discovered[cur.destroyCnt + 1][nextRow][nextCol] = true;
				}
			}
		}
		
		
		return -1;
	}
	
	private static boolean isMovable(int nextRow, int nextCol) {
		return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
	}
}