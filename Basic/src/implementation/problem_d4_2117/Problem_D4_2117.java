package implementation.problem_d4_2117;

import java.io.*;
import java.util.*;

public class Problem_D4_2117 {
	private static class Point {
		private int row;
		private int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public static int getDistance(Point p1, Point p2) {
			return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
		}
	}
	
	private static int N;
	private static int M;
	private static List<Point> houses;
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			houses = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(input.readLine());
				
				for (int j = 0; j < N; j++) {
					if (st.nextToken().equals("1")) {
						houses.add(new Point(i, j));
					}
				}
			}

			answer.append(String.format("#%d %d%n", tc, getMaxHouseCountGlobally()));
		}
		
		System.out.println(answer);
	}

	private static int getMaxHouseCountGlobally() {
		int maxCnt = 0;

		for (int k = 1; k <= N + 1; k++) {
			if (getCost(k) > M * houses.size()) {
				break;
			}

			maxCnt = Math.max(maxCnt, getMaxHouseCountLocally(k));
		}

		return maxCnt;
	}

	private static int getMaxHouseCountLocally(int size) {
		int maxCnt = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				maxCnt = Math.max(maxCnt, getHouseCount(new Point(row, col), size));
			}
		}

		return maxCnt;
	}

	private static int getHouseCount(Point center, int size) {
		int cnt = 0;

		for (int len = houses.size(), i = 0; i < len; i++) {
			if (Point.getDistance(center, houses.get(i)) < size) {
				cnt++;
			}
		}

		if (getCost(size) > M * cnt) {
			return Integer.MIN_VALUE;
		}

		return cnt;
	}

	private static int getCost(int size) {
		return (int) (Math.pow(size, 2) + Math.pow(size - 1, 2));
	}
}
