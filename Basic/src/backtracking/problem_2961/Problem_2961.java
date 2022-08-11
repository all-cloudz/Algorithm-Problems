package backtracking.problem_2961;

import java.io.*;
import java.util.*;

public class Problem_2961 {
	private static int N;
	private static long[][] ingredients;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(input.readLine());
		ingredients = new long[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(input.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}

		traversePowerSet(0, 0);

		System.out.println(min);
	}

	private static void traversePowerSet(int idx, int isSelected) {
		if (idx == N && isSelected == 0) {
			return;
		}
		
		if (idx == N) {
			min = Math.min(min, getDiff(isSelected));
			return;
		}

		traversePowerSet(idx + 1, isSelected | (1 << idx));
		traversePowerSet(idx + 1, isSelected);
	}

	private static int getDiff(int isSelected) {
		int sourScore = 1;
		int bitterScore = 0;

		for (int i = 0; i < N; i++) {
			if ((isSelected & (1 << i)) == 0) {
				continue;
			}

			sourScore *= ingredients[i][0];
			bitterScore += ingredients[i][1];
		}

		return Math.abs(sourScore - bitterScore);
	}
}
