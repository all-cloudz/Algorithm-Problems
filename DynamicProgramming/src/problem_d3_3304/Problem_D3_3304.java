package problem_d3_3304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_3304 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            String from = tokenizer.nextToken();
            String to = tokenizer.nextToken();
            answer.append(String.format("#%d %d%n", tc, getLengthOfLCS(from, to)));
        }

        System.out.println(answer);
    }

    private static int getLengthOfLCS(String from, String to) {
        int fromLen = from.length();
        int toLen = to.length();
        int[][] cache = new int[fromLen + 1][toLen + 1];

        for (int i = 1; i <= fromLen; i++) {
            for (int j = 1; j <= toLen; j++) {
                if (from.charAt(i - 1) == to.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                    continue;
                }

                cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
            }
        }

        return cache[fromLen][toLen];
    }


}
