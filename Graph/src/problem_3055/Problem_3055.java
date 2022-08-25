package problem_3055;

import java.io.*;
import java.util.*;

public class Problem_3055 {
	private static class Point {
		private int row;
		private int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	private static int R, C;
	private static char[][] map;
	private static List<Point> water;
	private static Point runner;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(input.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		water = new ArrayList<>();
		
		for (int i = 0; i < R; i++) {
			String line = input.readLine();
			
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if (map[i][j] == '*') {
					water.add(new Point(i, j));
					continue;
				}
				
				if (map[i][j] == 'S') {
					runner = new Point(i, j);
					map[i][j] = '.';
				}
			}
		}
		
		int time = run();
		System.out.println((time != -1) ? time : "KAKTUS");
	}
	
	private static int run() {
		Queue<Point> points = new ArrayDeque<>();
		boolean[][][] discovered = new boolean[2][R][C];
		
		points.offer(runner);
		discovered[0][runner.row][runner.col] = true;	
		
		points.addAll(water);
		for (Point cur : water) {
			discovered[1][cur.row][cur.col] = true;
		}
		
		int time = 0;
		while (!points.isEmpty()) {
			for (int levelSize = points.size(), level = 0; level < levelSize; level++) {
				Point cur = points.poll();
				char status = map[cur.row][cur.col];
				
				if (status == 'D') {
					return time;
				}
				
				for (int[] move : DIRECTIONS) {
					int nextRow = cur.row + move[0];
					int nextCol = cur.col + move[1];
					
					if (!isMovable(status, nextRow, nextCol)) {
						continue;
					}
					
					if (status == '.' && !discovered[0][nextRow][nextCol]) {
						points.offer(new Point(nextRow, nextCol));
						discovered[0][nextRow][nextCol] = true;
						continue;
					}
					
					if (status == '*' && !discovered[1][nextRow][nextCol]) {
						points.offer(new Point(nextRow, nextCol));
						discovered[1][nextRow][nextCol] = true;
						map[nextRow][nextCol] = '*';
					}
				}
			}
			
			time++;
		}
		
		return -1;
	}
	
	private static boolean isMovable(char status, int nextRow, int nextCol) {
		if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
			return false;
		}
		
		if (map[nextRow][nextCol] == 'X') {
			return false;
		}
		
		if (status == '.' && map[nextRow][nextCol] == '*') {
			return false;
		}
		
		if (status == '*' && map[nextRow][nextCol] == 'D') {
			return false;
		}
		
		return true;
	}
}
