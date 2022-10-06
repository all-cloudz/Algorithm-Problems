package problem_1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_1194 {
	
	private static class Point {
		private int row;
		private int col;
		private int keys;
		
		public Point(int row, int col, int keys) {
			super();
			this.row = row;
			this.col = col;
			this.keys = keys;
		}
	}

	private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final Set<Character> keySet;
	private static final Set<Character> doorSet;
	
	private static int N, M;
	private static char[][] map;
	private static Point start;
	
	static {
		keySet = new HashSet<>();
		keySet.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
		
		doorSet = new HashSet<>();
		doorSet.addAll(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = input.readLine();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if (map[i][j] == '0') {
					start = new Point(i, j, 0);
					map[i][j] = '.';
					continue;
				}
			}
		}
		
		System.out.println(escape());
	}
	
	private static int escape() {
		Queue<Point> route = new ArrayDeque<>();
		boolean[][][] discovered = new boolean[(1 << 7) - 1][N][M];
		
		int hasKeys = 0;
		route.offer(start);
		discovered[start.keys][start.row][start.col] = true;
		
		int moveCnt = 0;
		while (!route.isEmpty()) {
			for (int levelSize = route.size(), i = 0; i < levelSize; i++) {
				Point cur = route.poll();
				
				if (map[cur.row][cur.col]== '1') {
					return moveCnt;
				}
			
				for (int[] move : DIRECTIONS) {
					int nextRow = cur.row + move[0];
					int nextCol = cur.col + move[1];
					int nextKeys = cur.keys;
					
					if (!isMovable(nextRow, nextCol)) {
						continue;
					}
					
					if (discovered[cur.keys][nextRow][nextCol]) {
						continue;
					}
					
					if (doorSet.contains(map[nextRow][nextCol]) && ((cur.keys & 1 << (map[nextRow][nextCol] - 'A')) == 0)) {
						continue;
					}
					
					if (keySet.contains(map[nextRow][nextCol])) {
						nextKeys = cur.keys | 1 << (map[nextRow][nextCol] - 'a');
					}
					
					route.offer(new Point(nextRow, nextCol, nextKeys));
					discovered[nextKeys][nextRow][nextCol] = true;
				}
			}
			
			moveCnt++;
		}
		
		return -1;
	}
	
	private static boolean isMovable(int nextRow, int nextCol) {
		return (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M) && (map[nextRow][nextCol] != '#');
	}
	
}
