package problem_d3_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_5215_Tabulation_Sol2 {
    private static int N;
    private static int L;
    private static int[] tabulated;
    private static int[][] burger;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            tabulated = new int[L + 1];
            burger = new int[N + 1][2];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(input.readLine());
                burger[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // Score, Kcal
            }

            fillTable();
            answer.append(String.format("#%d %d%n", tc, tabulated[L]));
        }

        System.out.println(answer);
    }

    private static void fillTable() {
        for (int i = 1; i <= N; i++) {
            for (int j = L; j >= 1; j--) {
                if (burger[i][1] > j) {
                    continue;
                }

                tabulated[j] = Math.max(tabulated[j], burger[i][0] + tabulated[j - burger[i][1]]);
            }
        }
    }
}
