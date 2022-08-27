package implementation.problem_17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_17144 {

	private static final int[][] CLOCKWISE = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int[][] COUNTERCLOCKWISE = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static int R, C;
	private static int[][] map;
	private static int[] highCleaner;
	private static int[] lowCleaner;
	private static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(input.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(input.readLine());

			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == -1) {
					if (highCleaner == null) {
						highCleaner = new int[] { i, j };
						continue;
					}

					lowCleaner = new int[] { i, j };
				}
			}
		}

		while (T-- > 0) {
			spreadDust();
			clean();
		}

//		System.out.println(Arrays.stream(map).flatMapToInt(Arrays::stream).sum() + 2);
		int sum = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}

	private static void spreadDust() {
		int[][] tmp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] <= 4) {
					tmp[i][j] += map[i][j];
					continue;
				}

				int quantity = map[i][j] / 5;
				int cnt = 0;

				for (int[] move : CLOCKWISE) {
					int nextRow = i + move[0];
					int nextCol = j + move[1];

					if (isMovable(nextRow, nextCol)) {
						tmp[nextRow][nextCol] += quantity;
						cnt++;
					}
				}

				tmp[i][j] += map[i][j] - quantity * cnt;
			}
		}

		map = tmp;
	}

	private static void clean() {
		clean(highCleaner, false);
		clean(lowCleaner, true);
	}

	private static void clean(int[] cleaner, boolean isClockwise) {
		int curRow = cleaner[0];
		int curCol = cleaner[1];

		final int[][] DIRECTIONS = (isClockwise) ? CLOCKWISE : COUNTERCLOCKWISE;
		loop: for (int[] move : DIRECTIONS) {
			while (true) {
				int prevRow = curRow + move[0];
				int prevCol = curCol + move[1];

				if (!isMovable(cleaner, isClockwise, prevRow, prevCol)) {
					continue loop;
				}

				map[curRow][curCol] = map[prevRow][prevCol];
				curRow = prevRow;
				curCol = prevCol;
			}
		}

		map[cleaner[0]][cleaner[1]] = -1;
		map[cleaner[0]][cleaner[1] + 1] = 0;
	}

	private static boolean isMovable(int nextRow, int nextCol) {
		if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
			return false;
		}

		return map[nextRow][nextCol] != -1;
	}

	private static boolean isMovable(int[] cleaner, boolean isClockwise, int prevRow, int prevCol) {
		if (prevRow < 0 || prevRow >= R || prevCol < 0 || prevCol >= C) {
			return false;
		}

		if (isClockwise && prevRow < cleaner[0]) {
			return false;
		}

		if (!isClockwise && prevRow > cleaner[0]) {
			return false;
		}

		return map[prevRow][prevCol] != -1;
	}

}
