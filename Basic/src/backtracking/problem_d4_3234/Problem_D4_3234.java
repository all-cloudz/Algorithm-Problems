package backtracking.problem_d4_3234;

import java.io.*;
import java.util.*;

public class Problem_D4_3234 {
	private static int N;
	private static int[] weights;
	private static int[] selected;
	private static int totalCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		final int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(input.readLine());
			weights = new int[N];
			selected = new int[N];
			totalCnt = 0;

			StringTokenizer st = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			permutate(0, 0);

			answer.append(String.format("#%d %d%n", tc, totalCnt));
		}

		System.out.println(answer);
	}

	private static void permutate(int isSelected, int cnt) {
		if (cnt == N) {
			distribute(0, 0, 0);
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((isSelected & (1 << i)) != 0) {
				continue;
			}

			selected[cnt] = weights[i];
			permutate(isSelected | (1 << i), cnt + 1);
		}
	}

	private static void distribute(int idx, int sumOfLeft, int sumOfRight) {
		if (sumOfLeft < sumOfRight) {
			return;
		}

		if (idx == N) {
			totalCnt++;
			return;
		}

		distribute(idx + 1, sumOfLeft + selected[idx], sumOfRight);
		distribute(idx + 1, sumOfLeft, sumOfRight + selected[idx]);
	}
}