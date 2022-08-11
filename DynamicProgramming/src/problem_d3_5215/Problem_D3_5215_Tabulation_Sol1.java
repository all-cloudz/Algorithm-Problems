package problem_d3_5215;

import java.io.*;
import java.util.*;

// 제한 + 가치의 최대는 Knapsack Problem과 동치이고, 제한 + 개수의 최대는 Greedy 접근을 사용한다.
public class Problem_D3_5215_Tabulation_Sol1 {
    private static int N;
    private static int L;
    private static int[][] tabulated;
    private static int[][] burger;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            tabulated = new int[N + 1][L + 1];
            burger = new int[N + 1][2];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(input.readLine());
                burger[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // Score, Kcal
            }

            fillTable();
            answer.append(String.format("#%d %d%n", tc, tabulated[N][L]));
        }

        System.out.println(answer);
    }

    private static void fillTable() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= L; j++) {
                if (burger[i][1] > j) {
                    tabulated[i][j] = tabulated[i - 1][j];
                    continue;
                }

                int remainKcal = j - burger[i][1];
                tabulated[i][j] = Math.max(tabulated[i - 1][j], burger[i][0] + tabulated[i - 1][remainKcal]);
            }
        }
    }
}
