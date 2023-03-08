package problem_d4_13736;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D4_13736 {

    private static final long BASE = 2L;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            long A = Long.parseLong(tokenizer.nextToken());
            long B = Long.parseLong(tokenizer.nextToken());
            long K = Long.parseLong(tokenizer.nextToken());

            long sum = A + B;
            long resultOfA = (getPower(BASE, K, sum) * A) % sum;
            answer.append(String.format("#%d %d%n", tc, Math.min(resultOfA, sum - resultOfA)));
        }

        System.out.println(answer);
    }

    private static long getPower(long base, long expo, long divisor) {
        long power = 1;

        while (expo > 0) {
            if ((expo & 1) == 1) {
                power = (power * base) % divisor;
            }

            base = (base * base) % divisor;
            expo = expo >> 1;
        }

        return power;
    }

}
