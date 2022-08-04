package problem_9328;

import java.io.*;
import java.util.*;

public class Problem_9328_Sol1 {
	private static class Point {
		private int row;
		private int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static final int[][] DIRECTION = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private static StringBuilder answer;
	private static HashSet<Character> keys;
	private static HashMap<Character, Queue<Point>> doorsMap;
	
	private static int h;
	private static int w;
	private static char[][] map;
	private static List<Point> entrance;
	private static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuilder();

		int T = Integer.parseInt(input.readLine());
		while (T-- > 0) {
			keys = new HashSet<>();
			doorsMap = new HashMap<>();
			for (int i = 0; i < 26; i++) {
				doorsMap.putIfAbsent((char) ('A' + i), new ArrayDeque<>());
			}
			
			StringTokenizer tokenizer = new StringTokenizer(input.readLine());
			h = Integer.parseInt(tokenizer.nextToken());
			w = Integer.parseInt(tokenizer.nextToken());
			map = new char[h][w];
			
			for (int i = 0; i < h; i++) {
				map[i] = input.readLine().toCharArray();
			}
			
			char[] tmp = input.readLine().toCharArray();
			for (char cur : tmp) {
				if (cur == '0') {
					break;
				}
				
				keys.add(cur);
			}
			
			entrance = new ArrayList<>();
			cnt = 0;
			setEntrance();
			
			boolean[][] discovered = new boolean[h][w];
			for (Point p : entrance) {
				char cur = map[p.row][p.col];
				
				if ('A' <= cur && cur <= 'Z') {
					if (!keys.contains(Character.toLowerCase(cur))) {
						doorsMap.get(cur).add(p);
						continue;
					}
				}
				
				searchDoc(p, discovered);
			}
			
			answer.append(cnt).append('\n');
		}
		
		System.out.println(answer);
	}
	
	private static void setEntrance() {
		for (int i = 1; i < h; i++) {
			if (map[i][0] != '*') {
				setEntrance(i, 0);
			}
			
			if (map[i][w - 1] != '*') {
				setEntrance(i, w - 1);
			}
		}
		
		for (int j = 0; j < w; j++) {
			if (map[0][j] != '*') {
				setEntrance(0, j);
			}	
			
			if (map[h - 1][j] != '*') {
				setEntrance(h - 1, j);
			}
		}
	}
	
	private static void setEntrance(int row, int col) {
		if ('a' <= map[row][col] && map[row][col] <= 'z') {
			keys.add(map[row][col]);
			map[row][col] = '.';
		}
		
		if (map[row][col] == '$') {
			cnt++;
			map[row][col] = '.';
		}
		
		entrance.add(new Point(row, col));
	}
	
	private static void searchDoc(Point start, boolean[][] discovered) {
		Queue<Point> points = new LinkedList<>();
		
		points.offer(start);
		discovered[start.row][start.col] = true;
		
		while (!points.isEmpty()) {
			Point cur = points.poll();
			
			for (int i = 0; i < DIRECTION.length; i++) {
				int nextRow = cur.row + DIRECTION[i][0];
				int nextCol = cur.col + DIRECTION[i][1];
				
				if (nextRow < 0 || nextCol < 0 || nextRow >= h || nextCol >= w) {
					continue;
				}
				
				if (discovered[nextRow][nextCol]) {
					continue;
				}
				
				char next = map[nextRow][nextCol];
				
				if (next == '*') {
					continue;
				}
				
				if (next == '$') {
					cnt++;
				} else if ('a' <= next && next <= 'z') {
					keys.add(next);
					
					Queue<Point> doors = doorsMap.get(Character.toUpperCase(next));
					while (!doors.isEmpty()) {
						points.offer(doors.poll());
					}
				} else if ('A' <= next && next <= 'Z') {
					if (!keys.contains(Character.toLowerCase(next))) {
						doorsMap.get(next).add(new Point(nextRow, nextCol));
						continue;
					}
				}
				
				points.offer(new Point(nextRow, nextCol));
				discovered[nextRow][nextCol] = true;
			}
		}
	}
}
