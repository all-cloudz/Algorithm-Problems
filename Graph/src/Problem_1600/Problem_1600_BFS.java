package Problem_1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_1600_BFS {

	private static class Point {
		private int row;
		private int col;
		private int moveCnt;
		private int horseCnt;
		
		public Point(int row, int col, int moveCnt, int horseCnt) {
			this.row = row;
			this.col = col;
			this.moveCnt = moveCnt;
			this.horseCnt = horseCnt;
		}
	}
		
	private static int K;
	private static int W, H;
	private static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(input.readLine());
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		W = Integer.parseInt(tokenizer.nextToken());
		H = Integer.parseInt(tokenizer.nextToken());
		board = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		final int[][] NORMAL_DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		final int[][] HORSE_DIRECTIONS = { { -1, 2 }, { 1, 2 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { -2, -1 }, { -2, 1 } };
		
		Queue<Point> points = new ArrayDeque<>();
		boolean[][][] discovered = new boolean[H][W][K + 1];
		
		points.add(new Point(0, 0, 0, 0));
		discovered[0][0][0] = true;
		
		while (!points.isEmpty()) {
			Point cur = points.poll();
			
			if (board[cur.row][cur.col] == 1) {
				continue;
			}
			
			if (cur.row == H - 1 && cur.col == W - 1) {
				return cur.moveCnt;
			}
			
			for (int[] move : NORMAL_DIRECTIONS) {
				int nextRow = cur.row + move[0];
				int nextCol = cur.col + move[1];
				
				if (!isMovale(nextRow, nextCol)) {
					continue;
				}
				
				if (!discovered[nextRow][nextCol][cur.horseCnt]) {
					points.offer(new Point(nextRow, nextCol, cur.moveCnt + 1, cur.horseCnt));
					discovered[nextRow][nextCol][cur.horseCnt] = true;
				}
			}
			
			if (cur.horseCnt >= K) {
				continue;
			}
			
			for (int[] move : HORSE_DIRECTIONS) {
				int nextRow = cur.row + move[0];
				int nextCol = cur.col + move[1];
				
				if (!isMovale(nextRow, nextCol)) {
					continue;
				}
				
				if (!discovered[nextRow][nextCol][cur.horseCnt + 1]) {
					points.offer(new Point(nextRow, nextCol, cur.moveCnt + 1, cur.horseCnt + 1));
					discovered[nextRow][nextCol][cur.horseCnt + 1] = true;
				}
			}
		}
		
		return -1;
	}

	private static boolean isMovale(int nextRow, int nextCol) {
		return 0 <= nextRow && nextRow < H && 0 <= nextCol && nextCol < W;
	}
	
}
