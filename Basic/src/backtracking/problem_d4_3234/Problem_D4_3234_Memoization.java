package backtracking.problem_d4_3234;

import java.io.*;
import java.util.*;

public class Problem_D4_3234_Memoization {
    private static int N;
    private static int[] weights;
    private static int totalWeight;
    private static int[][] memoized;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            weights = new int[N];
            totalWeight = 0;

            StringTokenizer st = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
                totalWeight += weights[i];
            }

            memoized = new int[1 << N][totalWeight + 1];
            answer.append(String.format("#%d %d%n", tc, permutate(0, 0, 0, 0)));
        }

        System.out.println(answer);
    }

    private static int permutate(int used, int idx, int sumOfLeft, int sumOfRight) {
        if (sumOfLeft < sumOfRight) {
            return 0;
        }

        if (memoized[used][sumOfLeft] != 0) {
            return memoized[used][sumOfLeft];
        }

        if (idx == N) {
            return 1;
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if ((used & (1 << i)) != 0) {
                continue;
            }

            cnt += permutate(used | (1 << i), idx + 1, sumOfLeft + weights[i], sumOfRight)
                    + permutate(used | (1 << i), idx + 1, sumOfLeft, sumOfRight + weights[i]);
        }

        return memoized[used][sumOfLeft] = cnt;
    }
}
