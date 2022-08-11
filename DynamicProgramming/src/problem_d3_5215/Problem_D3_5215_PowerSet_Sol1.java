package problem_d3_5215;

import java.io.*;
import java.util.*;

public class Problem_D3_5215_PowerSet_Sol1 {
    private static int N;
    private static int L;
    private static int[][] burger;
    private static LinkedList<int[]> selected;
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
            selected = new LinkedList<>();
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());
                burger[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // Score, Kcal
            }

            traversePowerSet(0, 0);
            answer.append(String.format("#%d %d%n", tc, max));
        }

        System.out.println(answer);
    }

    private static void traversePowerSet(int idx, int kcalSum) {
        if (kcalSum > L) {
            return;
        }

        if (idx == N) {
            max = Math.max(max, getScoreSum());
            return;
        }

        selected.addLast(burger[idx]);
        traversePowerSet(idx + 1, kcalSum + burger[idx][1]);
        selected.removeLast();
        traversePowerSet(idx + 1, kcalSum);
    }

    private static int getScoreSum() {
        int sum = 0;

        for (int[] cur : selected) {
            sum += cur[0];
        }

        return sum;
    }
}
