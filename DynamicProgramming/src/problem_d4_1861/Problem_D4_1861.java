package problem_d4_1861;

import java.io.*;
import java.util.*;

public class Problem_D4_1861 {
	private static class Point {
		private int row;
		private int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	private static int N;
	private static int[][] rooms;
	private static int[][] memoize;
	private static int start;
	private static int maxCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine());
			rooms = new int[N + 2][N + 2];
			memoize = new int[N + 2][N + 2];
			start = Integer.MAX_VALUE;
			maxCnt = Integer.MIN_VALUE;
			
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(input.readLine());
				
				for (int j = 1; j <= N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int cnt = getMaxRoomsCount(i, j);
					
					if (maxCnt < cnt) {
						maxCnt = cnt;
						start = rooms[i][j];
						continue;
					} 
					
					if (maxCnt == cnt) {
						start = Math.min(start, rooms[i][j]);
					}
				}
			}
			
			answer.append(String.format("#%d %d %d%n", tc, start, maxCnt));
		}
		
		System.out.println(answer);
	}
	
	private static int getMaxRoomsCount(int row, int col) {
		if (memoize[row][col] != 0) {
			return memoize[row][col];
		}
		
		memoize[row][col] = 1;
		
		for (int i = 0; i < DIRECTIONS.length; i++) {
			int nextRow = row + DIRECTIONS[i][0];
			int nextCol = col + DIRECTIONS[i][1];
			
			if (rooms[nextRow][nextCol] - rooms[row][col] == 1) {
				memoize[row][col] = getMaxRoomsCount(nextRow, nextCol) + 1;
			}
		}
		
		return memoize[row][col];
	}
}
