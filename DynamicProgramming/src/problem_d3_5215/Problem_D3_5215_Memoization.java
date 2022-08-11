package problem_d3_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_5215_Memoization {
    private static int N;
    private static int L;
    private static int[][] memoized;
    private static int[][] burger;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            memoized = new int[N + 1][L + 1];
            burger = new int[N + 1][2];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(input.readLine());
                burger[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // Score, Kcal
            }

            fillTable();
            answer.append(String.format("#%d %d%n", tc, memoized[N][L]));
        }

        System.out.println(answer);
    }

    private static void fillTable() {
        fillTable(N, L);
    }

    private static int fillTable(int row, int col) {
        if (row == 0) {
            return 0;
        }

        if (memoized[row][col] != 0) {
            return memoized[row][col];
        }

        if (burger[row][1] > col) {
            memoized[row][col] = fillTable(row - 1, col);
        } else {
            memoized[row][col] = Math.max(fillTable(row - 1, col), burger[row][0] + fillTable(row - 1, col - burger[row][1]));
        }

        return memoized[row][col];
    }
}
