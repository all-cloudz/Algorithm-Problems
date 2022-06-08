package problem_1904;

import java.io.*;

public class Problem_1904_Tabulation {
    private static final int DIVIDER = 15746;

    private static int[] tabulate;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        tabulate = new int[N + 1];

        System.out.print(count(N));
    }

    private static int count(int N) {
        if (N == 1) {
            return 1;
        }

        tabulate[1] = 1;
        tabulate[2] = 2;

        for (int i = 3; i <= N; i++) {
            tabulate[i] = (tabulate[i - 1] + tabulate[i - 2]) % DIVIDER;
        }

        return tabulate[N];
    }
}
