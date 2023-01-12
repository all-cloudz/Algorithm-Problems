package problem_1034;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1034 {

    private static int N, M;
    private static char[][] lamps;
    private static int[] counts;
    private static int K;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        lamps = new char[N][M];
        counts = new int[N];

        for (int i = 0; i < N; i++) {
            String line = input.readLine();

            for (int j = 0; j < M; j++) {
                lamps[i][j] = line.charAt(j);

                if (lamps[i][j] == '0') {
                    counts[i]++;
                }
            }
        }

        K = Integer.parseInt(input.readLine());
        max = 0;

        for (int i = 0; i < N; i++) {
            if (K >= counts[i] && ((K - counts[i]) & 1) == 0) {
                max = Math.max(max, countSameRow(i));
            }
        }

        System.out.println(max);
    }

    private static int countSameRow(int row) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (Arrays.equals(lamps[i], lamps[row])) {
                count++;
            }
        }

        return count;
    }

}
