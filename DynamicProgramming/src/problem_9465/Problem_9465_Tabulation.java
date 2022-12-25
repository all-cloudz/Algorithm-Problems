package problem_9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_9465_Tabulation {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(input.readLine());
            int[][] stickers = new int[2][n];
            for (int i = 0; i < 2; i++) {
                stickers[i] = Arrays.stream(input.readLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
            }

            answer.append(maxScore(stickers)).append("\n");
        }

        System.out.println(answer);
    }

    private static int maxScore(int[][] stickers) {
        if (n == 1) {
            return Math.max(stickers[0][0], stickers[1][0]);
        }

        int[][] cache = new int[2][n];
        cache[0][0] = stickers[0][0];
        cache[0][1] = stickers[1][0] + stickers[0][1];
        cache[1][0] = stickers[1][0];
        cache[1][1] = stickers[0][0] + stickers[1][1];

        for (int i = 2; i < n; i++) {
            cache[0][i] = stickers[0][i] + Math.max(cache[1][i - 1], cache[1][i - 2]);
            cache[1][i] = stickers[1][i] + Math.max(cache[0][i - 1], cache[0][i - 2]);
        }

        return Math.max(cache[0][n - 1], cache[1][n - 1]);
    }

}
