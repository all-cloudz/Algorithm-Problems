package math.problem_11444;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_11444 {

    private static final int DIVISOR = 1_000_000_007;
    private static final long[][] FIBONACCI_BASE = new long[][] {
            { 1, 1 },
            { 1, 0 }
    };

    private static final long[][] INIT_VALUE = new long[][] {
            { 1 },
            { 0 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(input.readLine());
        long[][] fibonacciFactor = getPower(FIBONACCI_BASE, n - 1);
        long[][] result = multiplyMatrix(fibonacciFactor, INIT_VALUE);

        System.out.println(result[0][0]);
    }

    private static long[][] getPower(long[][] base, long expo) {
        if (expo == 0) {
            return base;
        }

        if (expo == 1) {
            return base;
        }

        long[][] result = getPower(base, expo / 2);
        result = multiplyMatrix(result, result);

        if ((expo & 1) != 0) {
            result = multiplyMatrix(result, base);
        }

        return result;
    }

    private static long[][] multiplyMatrix(long[][] matrix1, long[][] matrix2) {
        int row = matrix1.length;
        int col = matrix2[0].length;
        long[][] result = new long[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < col; k++) {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]) % DIVISOR;
                }
            }
        }

        return result;
    }

}
