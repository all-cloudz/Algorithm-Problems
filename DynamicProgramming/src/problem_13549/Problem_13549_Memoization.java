package problem_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_13549_Memoization {

    private static int N;
    private static int K;
    private static int[] memoized;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        memoized = new int[K + 1];
        Arrays.fill(memoized, -1);
        for (int i = 0; i <= N; i++) {
            memoized[i] = N - i;
        }

        memoize(K);
        System.out.println(memoize(K));
    }

    private static int memoize(int cur) {
        if (memoized[cur] != -1) {
            return memoized[cur];
        }

        if (cur % 2 == 0) {
            return memoized[cur] = Math.min(memoize(cur >> 1), memoize(cur - 1) + 1);
        }

        memoized[cur] = Math.min(memoize((cur - 1) >> 1), memoize((cur + 1) >> 1)) + 1;
        return memoized[cur] = Math.min(memoized[cur], memoize(cur - 1) + 1);
    }

}
