package problem_9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_9465_Memoization {

    private static final int DEFAULT_VALUE = Integer.MIN_VALUE;

    private static int[][] stickers;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(input.readLine());
            stickers = new int[2][n];
            for (int i = 0; i < 2; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());

                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            cache = new int[2][n];
            for (int i = 0; i < 2; i++) {
                Arrays.fill(cache[i], DEFAULT_VALUE);
            }

            answer.append(Math.max(maxScore(0, n - 1), maxScore(1, n - 1))).append("\n");
        }

        System.out.println(answer);
    }

    private static int maxScore(int row, int col) {
        if (col < 0) {
            return 0;
        }

        if (col == 0) {
            return cache[row][col] = stickers[row][col];
        }

        if (cache[row][col] != DEFAULT_VALUE) {
            return cache[row][col];
        }

        return cache[row][col] = stickers[row][col] + Math.max(maxScore((row + 1) % 2, col - 1), maxScore((row + 1) % 2, col - 2));
    }

}
