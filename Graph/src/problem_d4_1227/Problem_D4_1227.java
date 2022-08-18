package problem_d4_1227;

import java.io.*;
import java.util.*;

public class Problem_D4_1227 {
	private static class Point {
		private int row;
		private int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (o == null || !(o instanceof Point)) {
				return false;
			}
			
			Point that = (Point) o;
			return this.row == that.row && this.col == that.col;
		}
	}
	
	private static char[][] map;
	private static Point departure;
	private static Point arrival;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			input.readLine();
			map = new char[100][100];
			
			for (int i = 0; i < 100; i++) {
				String line = input.readLine();
				
				for (int j = 0; j < 100; j++) {
					map[i][j] = line.charAt(j);
					
					if (map[i][j] == '2') {
						departure = new Point(i, j);
						continue;
					}
					
					if (map[i][j] == '3') {
						arrival = new Point(i, j);
					}
				}
			}
			
			answer.append(String.format("#%d %d%n", tc, escape()));
		}
		
		System.out.println(answer);
	}
	
	private static int escape() {
		final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		
		Queue<Point> points = new ArrayDeque<>();
		boolean[][] discovered = new boolean[100][100];
		
		points.offer(departure);
		discovered[departure.row][departure.col] = true;
		
		while (!points.isEmpty()) {
			Point cur = points.poll();
			
			if (cur.equals(arrival)) {
				return 1;
			}
			
			for (int[] move : DIRECTIONS) {
				int nextRow = cur.row + move[0];
				int nextCol = cur.col + move[1];
				
				if (!isMovable(nextRow, nextCol)) {
					continue;
				}
				
				if (discovered[nextRow][nextCol]) {
					continue;
				}
				
				points.offer(new Point(nextRow, nextCol));
				discovered[nextRow][nextCol] = true;
			}
		}
		
		return 0;
	}
	
	private static boolean isMovable(int nextRow, int nextCol) {
		return (0 <= nextRow && nextRow < 100 && 0 <= nextCol && nextCol < 100)
				&& (map[nextRow][nextCol] != '1');
	}
}
