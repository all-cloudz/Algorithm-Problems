package math.problem_d3_5607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_5607 {

    private static final long DIVISOR = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int R = Integer.parseInt(tokenizer.nextToken());

            answer.append(String.format("#%d %d%n", tc, comb(N, R)));
        }

        System.out.println(answer);
    }

    private static long comb(int N, int R) {
        long facOfN = factorial(N);
        long facOfK = factorial(R);
        long facOfNK = factorial(N - R);

        return facOfN * pow(facOfK * facOfNK % DIVISOR, DIVISOR - 2) % DIVISOR;
    }

    private static long factorial(int num) {
        long fac = 1;

        while (num > 1) {
            fac = (fac * num--) % DIVISOR;
        }

        return fac;
    }

    private static long pow(long base, long expo) {
        if (expo == 1) {
            return base % DIVISOR;
        }

        long part = pow(base, expo >> 1);
        long result = part * part % DIVISOR;

        if ((expo & 1) == 1) {
            result = result * base % DIVISOR;
        }

        return result;
    }

}
