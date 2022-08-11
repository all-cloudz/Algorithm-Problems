package problem_d3_5215;

import java.io.*;
import java.util.*;

public class Problem_D3_5215_PowerSet_Sol2 {
    private static int N;
    private static int L;
    private static int[][] burger;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            burger = new int[N][2];
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                burger[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // Score, Kcal
            }

            traversePowerSet(0, 0, 0);
            answer.append(String.format("#%d %d%n", tc, max));
        }

        System.out.println(answer);
    }

    private static void traversePowerSet(int idx, int scoreSum, int kcalSum) {
        if (kcalSum > L) {
            return;
        }

        if (idx == N) {
            max = Math.max(max, scoreSum);
            return;
        }

        traversePowerSet(idx + 1, scoreSum + burger[idx][0], kcalSum + burger[idx][1]);
        traversePowerSet(idx + 1, scoreSum, kcalSum);
    }
}
