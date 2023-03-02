package problem_d4_11446;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

public class Problem_D4_11446 {

    private static int N;
    private static long M;
    private static long[] candies;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Long.parseLong(tokenizer.nextToken());

            candies = new long[N];
            tokenizer = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++) {
                candies[i] = Long.parseLong(tokenizer.nextToken());
            }

            answer.append(String.format("#%d %d%n", tc, maxCountOfCandyBag()));
        }

        System.out.println(answer);
    }

    private static long maxCountOfCandyBag() {
        long left = 0;
        long right = (long) Math.pow(10, 18) + 1;

        while (left < right) {
            long mid = left + (right - left >> 1);

            if (mid == 0) {
                return 0;
            }

            if (countTotalCandy(mid) >= M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

    private static long countTotalCandy(long candyBagCount) {
        return Arrays.stream(candies)
                     .flatMap(candy -> LongStream.of(candy / candyBagCount))
                     .sum();
    }

}
