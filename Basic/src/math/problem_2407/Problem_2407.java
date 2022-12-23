package math.problem_2407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Problem_2407 {

    private static BigInteger[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        cache = new BigInteger[n + 1][n + 1];

        System.out.println(combination(n, m));
    }

    private static BigInteger combination(int n, int m) {
        if (n == m || m == 0) {
            return BigInteger.ONE;
        }

        if (cache[n][m] != null) {
            return cache[n][m];
        }

        return cache[n][m] = combination(n - 1, m - 1).add(combination(n - 1, m));
    }

}
