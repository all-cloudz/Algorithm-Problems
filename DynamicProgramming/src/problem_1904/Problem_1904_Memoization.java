package problem_1904;

import java.io.*;

public class Problem_1904_Memoization {
    private static int[] memoize;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        memoize = new int[N + 1];

        System.out.print(count(N));
    }

    private static int count(int N) {
        if (N == 1) {
            memoize[N] = 1;
            return 1;
        }

        if (N == 2) {
            memoize[N] = 2;
            return 2;
        }

        if (memoize[N] != 0) {
            return memoize[N];
        }

        memoize[N] = (count(N - 1) + count(N - 2)) % 15746;
        return memoize[N];
    }
}
