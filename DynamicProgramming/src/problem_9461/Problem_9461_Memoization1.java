package problem_9461;

import java.io.*;
import java.util.*;

public class Problem_9461_Memoization1 {
    private static StringBuilder answer = new StringBuilder();
    private static List<Long> memoize;

    public static void main(String[] args)throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        memoize = new ArrayList<>(Arrays.asList(1L, 1L, 1L));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            final int N = Integer.parseInt(input.readLine());
            answer.append(fibonacci(N)).append('\n');
        }

        System.out.print(answer);
    }

    private static long fibonacci(int n) {
        if (n <= memoize.size()) {
            return memoize.get(n - 1);
        }

        fibonacci(n - 1);
        memoize.add(fibonacci(n - 2) + fibonacci(n - 3));

        return memoize.get(n - 1);
    }
}
