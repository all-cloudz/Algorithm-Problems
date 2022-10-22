package prefixSum.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[][] prefixSum = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + Integer.parseInt(tokenizer.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i <= n - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                max = Math.max(max, prefixSum[i + k][j + k] - prefixSum[i][j + k] - prefixSum[i + k][j] + prefixSum[i][j]);
            }
        }

        System.out.println(max);
    }

}
