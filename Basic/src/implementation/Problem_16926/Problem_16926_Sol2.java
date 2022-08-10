package implementation.Problem_16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_16926_Sol2 {
	private static final int[][] DIRECTION = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static int N;
	private static int M;
	private static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		final int R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());

			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] rotation = rotate(R);

//		for (int i = 0; i < N; i++) {
//			answer.append(Arrays.toString(arr[i]).replaceAll("\\[|\\]|\\,", "")).append('\n');
//		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answer.append(rotation[i][j]).append(' ');
			}

			answer.append('\n');
		}

		System.out.println(answer);
	}

	private static int[][] rotate(int cnt) {
		int rotateCnt = Math.min(N, M) / 2;

		int[][] rotation = new int[N][M];

		for (int i = 0; i < rotateCnt; i++) {
			int[] curPoint = { i, i };
			int cycle = 2 * ((N - 2 * i) + (M - 2 * i)) - 4;

			int idx = 0;
			for (int j = 0; j < cycle; j++) {
				int[] prevPoint = getPrevPoint(curPoint, cnt, i);
				rotation[curPoint[0]][curPoint[1]] = arr[prevPoint[0]][prevPoint[1]];

				int nextRow = curPoint[0] + DIRECTION[idx][0];
				int nextCol = curPoint[1] + DIRECTION[idx][1];

				if (!isExist(nextRow, nextCol, i)) {
					idx++;
					nextRow = curPoint[0] + DIRECTION[idx][0];
					nextCol = curPoint[1] + DIRECTION[idx][1];
				}

				curPoint = new int[] { nextRow, nextCol };
			}
		}

		return rotation;
	}
	
	private static int[] getPrevPoint(int[] point, int cnt, int limit) {
		int[] prevPoint = new int[2];
		prevPoint[0] = point[0];
		prevPoint[1] = point[1];
		
		cnt %= 2 * ((N - 2 * limit) + (M - 2 * limit)) - 4;
		while (cnt > 0) {
			if (prevPoint[0] == limit) {
				int dist = Math.min(cnt, M - limit - 1 - prevPoint[1]);
				prevPoint[1] += dist;
				cnt -= dist;
			}
			
			if (prevPoint[1] == M - limit - 1) {
				int dist = Math.min(cnt, N - limit - 1 - prevPoint[0]);
				prevPoint[0] += dist;
				cnt -= dist;
			}
			
			if (prevPoint[0] == N - limit - 1) {
				int dist = Math.min(cnt, prevPoint[1] - limit);
				prevPoint[1] -= dist;
				cnt -= dist;
			}
			
			if (prevPoint[1] == limit) {
				int dist = Math.min(cnt, prevPoint[0] - limit);
				prevPoint[0] -= dist;
				cnt -= dist;
			}
		}
		
		return prevPoint;
	}

	private static boolean isExist(int row, int col, int limit) {
		return limit <= row && row < N - limit && limit <= col && col < M - limit;
	}
}
