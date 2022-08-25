package implementation.problem_23291;

import java.io.*;
import java.util.*;

public class Problem_23291 {
	private static int N;
	private static int[][] fishBowl;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(input.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		fishBowl = new int[11][N];
		st = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			fishBowl[0][i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		while (diffOfMinMax() > K) {
			organize();
			answer++;
		}
		
		System.out.println(answer);
	}

	private static int diffOfMinMax() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			min = Math.min(min, fishBowl[0][i]);
			max = Math.max(max, fishBowl[0][i]);
		}

		return max - min;
	}

	private static void organize() {
		step1();

		int left = 0;
		int right = 1;
		int height = 1;
		for (int cnt = 0;; cnt++) {
			if (!step2(left, right, height)) {
				break;
			}

			left = right;
			right += height;
			height += (cnt % 2 == 0) ? 1 : 0;
		}

		step3(left, right, height);
		step4(left, right, height);
		step5();
	}

	// 물고기의 수가 가장 적은 어항에 물고기를 한 마리 넣기
	private static void step1() {
		int min = 10_001;
		List<Integer> minArgs = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (min < fishBowl[0][i]) {
				continue;
			}

			if (min > fishBowl[0][i]) {
				minArgs.clear();

				min = fishBowl[0][i];
			}

			minArgs.add(i);
		}

		for (int idx : minArgs) {
			fishBowl[0][idx]++;
		}
	}

	private static boolean step2(int left, int right, int height) {
		if (right + height > N) {
			return false;
		}

		int[][] rotated = rotate(left, right, height);

		for (int i = 1; i <= right - left; i++) {
			System.arraycopy(rotated[i - 1], 0, fishBowl[i], right, height);
		}
		
		return true;
	}

	private static int[][] rotate(int left, int right, int height) {
		int[][] rotated = new int[right - left][height];
		
		for (int idx = 0, i = right - 1; i >= left; idx++, i--) {
			for (int j = 0; j < height; j++) {
				rotated[idx][j] = fishBowl[j][i];
				fishBowl[j][i] = 0;
			}
		}

		return rotated;
	}

	private static void step3(int left, int right, int height) {
		final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		
		int[][] tmp = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = left; i < N; i++) {
			for (int j = 0; j < height; j++) {
				visited[j][i] = true;

				for (int[] move : DIRECTIONS) {
					int nextRow = j + move[0];
					int nextCol = i + move[1];
					
					if (nextRow < 0 || nextRow >= height || nextCol < left || nextCol >= N) {
						continue;
					}
					
					if (fishBowl[nextRow][nextCol] == 0) {
						continue;
					}
					
					if (visited[nextRow][nextCol]) {
						continue;
					}

					int d = (fishBowl[j][i] - fishBowl[nextRow][nextCol]) / 5;
					
					tmp[j][i] -= d;
					tmp[nextRow][nextCol] += d;
				}
			}
		}

		for (int i = left; i < N; i++) {
			for (int j = 0; j < height; j++) {
				fishBowl[j][i] += tmp[j][i];
			}
		}
	}
	
	private static void step4(int left, int right, int height) {
		int[][] tmp = new int[101][N];
		
		int idx = 0;
		for (int i = left; i < N; i++) {
			for (int j = 0; j < height; j++) {
				if (fishBowl[j][i] == 0) {
					continue;
				}
				
				tmp[0][idx++] = fishBowl[j][i];
			}
		}
		
		fishBowl = tmp;
	}
	
	private static void step5() {
		for (int i = 0; i < N / 2; i++) {
			fishBowl[1][N - 1 - i] = fishBowl[0][i];
			fishBowl[0][i] = 0;
		}
		
		for (int i = 0; i < N / 4; i++) {
			for (int j = 1; j >= 0; j--) {
				fishBowl[3 - j][N - 1 - i] = fishBowl[j][i + N / 2];
			}
		}
		
		step3(3 * N / 4, N, 4);
		step4(3 * N / 4, N, 4);
	}

//	private static int size() {
//		int size = 0;
//
//		int tmp = N;
//		int value = 10;
//
//		while (tmp > 0) {
//			tmp -= value / 10;
//			value += 5;
//			size++;
//		}
//
//		return size - 1;
//	}
}
