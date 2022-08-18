package implementation.problem_d4_5644;

import java.io.*;
import java.util.*;

public class Problem_D4_5644 {
	private static class AP {
		private int rowOfCenter;
		private int colOfCenter;
		private int coverage;
		private int power;

		public AP(int rowOfCenter, int colOfCenter, int coverage, int power) {
			this.rowOfCenter = rowOfCenter;
			this.colOfCenter = colOfCenter;
			this.coverage = coverage;
			this.power = power;
		}
	}

	private static class Point {
		private int row;
		private int col;
		private List<AP> aps;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
			this.aps = new ArrayList<>();
		}

		private static int getDistance(Point p1, Point p2) {
			return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
		}
	}

	private static final int[][] DIRECTIONS = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static int MAP_SIZE = 10;

	private static int max;
	private static Point[][] map;
	private static int M;
	private static int[] moveOfA;
	private static int[] moveOfB;
	private static int A;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			map = new Point[MAP_SIZE][MAP_SIZE];

			for (int i = 0; i < MAP_SIZE; i++) {
				for (int j = 0; j < MAP_SIZE; j++) {
					map[i][j] = new Point(i, j);
				}
			}

			StringTokenizer st = new StringTokenizer(input.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			moveOfA = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			moveOfB = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(input.readLine());
				
				int col = Integer.parseInt(st.nextToken()) - 1;
				int row = Integer.parseInt(st.nextToken()) - 1;
				int coverage = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				setAP(new AP(row, col, coverage, power));
			}

			setMax();
			answer.append(String.format("#%d %d%n", tc, max));
		}

		System.out.println(answer);
	}

	private static void setAP(AP ap) {
		for (int sizeOfRow = ap.rowOfCenter + ap.coverage, i = ap.rowOfCenter - ap.coverage; i <= sizeOfRow; i++) {
			for (int sizeOfCol = ap.colOfCenter + ap.coverage, j = ap.colOfCenter - ap.coverage; j <= sizeOfCol; j++) {
				if (i < 0 || i >= MAP_SIZE || j < 0 || j >= MAP_SIZE) {
					continue;
				}

				if (Point.getDistance(map[i][j], map[ap.rowOfCenter][ap.colOfCenter]) > ap.coverage) {
					continue;
				}

				map[i][j].aps.add(ap);
			}
		}
	}

	private static void setMax() {
		Point userA = map[0][0];
		Point userB = map[9][9];

		max += charge(userA, userB);

		for (int i = 0; i < M; i++) {
			userA = move(userA, moveOfA[i]);
			userB = move(userB, moveOfB[i]);

			max += charge(userA, userB);
		}
	}

	private static int charge(Point userA, Point userB) {
		int sizeOfA = userA.aps.size();
		int sizeOfB = userB.aps.size();

		if (sizeOfA == 0 && sizeOfB == 0) {
			return 0;
		}

		if (sizeOfA != 0 && sizeOfB == 0) {
			return getMaxCharge(userA);
		}

		if (sizeOfA == 0) {
			return getMaxCharge(userB);
		}

		int maxCharge = Integer.MIN_VALUE;

		for (int i = 0; i < sizeOfA; i++) {
			for (int j = 0; j < sizeOfB; j++) {
				AP curA = userA.aps.get(i);
				AP curB = userB.aps.get(j);

				if (curA.rowOfCenter == curB.rowOfCenter && curA.colOfCenter == curB.colOfCenter) {
					maxCharge = Math.max(maxCharge, curA.power);
					continue;
				}

				maxCharge = Math.max(maxCharge, curA.power + curB.power);
			}
		}

		return maxCharge;
	}

	private static int getMaxCharge(Point user) {
		int maxCharge = Integer.MIN_VALUE;

		for (AP cur : user.aps) {
			maxCharge = Math.max(maxCharge, cur.power);
		}

		return maxCharge;
	}

	private static Point move(Point user, int move) {
		int nextRow = user.row + DIRECTIONS[move][0];
		int nextCol = user.col + DIRECTIONS[move][1];
		return map[nextRow][nextCol];
	}
}
