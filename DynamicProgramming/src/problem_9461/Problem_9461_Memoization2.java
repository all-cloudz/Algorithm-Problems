package problem_9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_9461_Memoization2 {
    private static StringBuilder answer = new StringBuilder();
    private static long[] memoize = new long[101];

    public static void main(String[] args)throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        memoize[1] = 1L;
        memoize[2] = 1L;
        memoize[3] = 1L;

        final int T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            final int N = Integer.parseInt(input.readLine());
            answer.append(fibonacci(N)).append('\n');
        }

        System.out.print(answer);
    }

    private static long fibonacci(int n) {
        if (memoize[n] != 0) {
            return memoize[n];
        }

        return memoize[n] = fibonacci(n - 2) + fibonacci(n - 3);
    }
}
