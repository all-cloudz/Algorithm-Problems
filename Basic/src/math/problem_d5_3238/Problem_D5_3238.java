package math.problem_d5_3238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D5_3238 {

    private static long[] factorial;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            long n = Long.parseLong(tokenizer.nextToken());
            long k = Long.parseLong(tokenizer.nextToken());
            int p = Integer.parseInt(tokenizer.nextToken());
            answer.append(String.format("#%d %d%n", tc, comb(n, k, p)));
        }

        System.out.println(answer);
    }

    private static long comb(long n, long k, int p) {
        factorial = new long[p];
        factorial[0] = 1;
        for (int i = 1; i < p; i++) {
            factorial[i] = factorial[i - 1] * i % p;
        }

        long result = 1;

        // 뤼카의 정리
        while (n > 0 || k > 0) {
            int a = (int) (n % p);
            int b = (int) (k % p);

            if (a < b) {
                return 0;
            }

            // 페르마의 소정리
            result = result * factorial[a] * pow(factorial[b] * factorial[a - b] % p, p - 2, p) % p;
            n /= p;
            k /= p;
        }

        return result;
    }

    private static long factorial(long num, int p) {
        long fac = 1;

        while (num != 0) {
            int a = (int) (num % p);
            fac = fac * factorial[a] % p;
            num /= p;
        }

        return fac;
    }

    private static long pow(long base, long expo, int p) {
        if (expo == 1) {
            return base % p;
        }

        long half = pow(base, expo >> 1, p);
        long result = half * half % p;

        if ((expo & 1) == 1) {
            result = result * base % p;
        }

        return result;
    }

}
