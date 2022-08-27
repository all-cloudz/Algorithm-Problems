package implementation.problem_d4_battel2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_D4_Battle2_Sol2 {

	private static class Point {
		private int row;
		private int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public static int distance(Point p1, Point p2) {
			return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
		}
	}

	private static int N;
	private static int M;

	private static int[] order;
	private static boolean[] isSelected;
	private static int[] selected;

	private static Point[] customers, monsters;
	private static int minTime;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine());
			M = 0;

			customers = new Point[5];
			monsters = new Point[5];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(input.readLine());

				for (int j = 0; j < N; j++) {
					int cur = Integer.parseInt(st.nextToken());

					if (cur == 0) {
						continue;
					}

					M++;

					if (cur > 0) {
						monsters[cur] = new Point(i, j);
						continue;
					}

					if (cur < 0) {
						customers[-cur] = new Point(i, j);
					}
				}
			}

			order = new int[M];
			isSelected = new boolean[M];
			selected = new int[M];

			for (int num = 1, i = 0; i < M - 1; num++, i += 2) {
				order[i] = num;
				order[i + 1] = num;
			}

			minTime = Integer.MAX_VALUE;
			do {
				minTime = Math.min(minTime, getTime());
			} while (nextPermutate());
			
			answer.append(String.format("#%d %d%n", tc, minTime));
		}

		System.out.println(answer);
	}

	private static int getTime() {
		Point hunter = new Point(0, 0);
		boolean[] hunted = new boolean[5];

		int time = 0;
		for (int cur : order) {
			if (!hunted[cur]) {
				time += Point.distance(hunter, monsters[cur]);
				hunter = monsters[cur];
				hunted[cur] = true;
				continue;
			}

			time += Point.distance(hunter, customers[cur]);
			hunter = customers[cur];
		}

		return time;
	}

	private static boolean nextPermutate() {
		int i = M - 1;
		while (i > 0 && order[i - 1] >= order[i]) {
			i--;
		}
		
		if (i == 0) {
			return false;
		}
		
		int j = M - 1;
		while (order[j] <= order[i - 1]) {
			j--;
		}
		swap(i - 1, j);
		
		int k = M - 1;
		while (i < k) {
			swap(i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int i, int j) {
		int tmp = order[i];
		order[i] = order[j];
		order[j] = tmp;
	}

}
