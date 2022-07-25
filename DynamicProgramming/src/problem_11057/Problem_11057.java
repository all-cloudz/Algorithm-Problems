package problem_11057;

import java.io.*;

public class Problem_11057 {
    private static final int DIVISOR = 10_007;

    private static int N;
    private static long[][] tabulate;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        tabulate = new long[N + 1][10];
        fillTable();

        long sumRemainder = 0;
        for (long cur : tabulate[N]) {
            sumRemainder += cur;
        }

        System.out.println(sumRemainder % DIVISOR);
    }

    private static void fillTable() {
        for (int i = 1; i <= N; i++) {
            tabulate[i][0] = 1;
        }

        for (int j = 0; j < 10; j++) {
            tabulate[1][j] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < 10; j++) {
                tabulate[i][j] = (tabulate[i][j - 1] + tabulate[i - 1][j]) % DIVISOR;
            }
        }
    }
}
