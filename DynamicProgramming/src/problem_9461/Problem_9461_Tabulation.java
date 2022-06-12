package problem_9461;

import java.io.*;

public class Problem_9461_Tabulation {
    private static StringBuilder answer = new StringBuilder();
    private static long[] tabulate = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        tabulate[1] = 1L;
        tabulate[2] = 1L;
        tabulate[3] = 1L;

        final int T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            final int N = Integer.parseInt(input.readLine());
            answer.append(fibonacci(N)).append('\n');
        }

        System.out.print(answer);
    }

    private static long fibonacci(int n) {
        for (int i = 4; i <= n; i++) {
            tabulate[i] = tabulate[i - 2] + tabulate[i - 3];
        }

        return tabulate[n];
    }
}
