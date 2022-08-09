package backtracking.problem_d3_9229;

import java.io.*;
import java.util.*;

public class Problem_D3_9229_Backtracking {
    private static int N;
    private static int M;
    private static int[] weights;
    private static int[] selected;
    private static boolean[] isSelected;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            max = Integer.MIN_VALUE;

            weights = new int[N];
            selected = new int[2];
            isSelected = new boolean[N];

            st = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            chooseTwo(0);

            answer.append(String.format("#%d %d%n", tc, (max == Integer.MIN_VALUE) ? -1 : max));
        }

        System.out.println(answer);
    }

    private static void chooseTwo(int cnt) {
        if (cnt == 2) {
            int sum = 0;
            for (int i = 0; i < 2; i++) {
                sum += selected[i];
            }

            if (sum <= M) {
                max = Math.max(sum, max);
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) {
                continue;
            }

            selected[cnt] = weights[i];
            isSelected[i] = true;
            chooseTwo(cnt + 1);
            isSelected[i] = false;
        }
    }
}