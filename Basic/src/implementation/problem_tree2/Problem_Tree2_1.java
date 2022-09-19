package implementation.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[] prefixSum = new int[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(tokenizer.nextToken());
        }

        int max = Integer.MIN_VALUE;
        for (int i = k + 1; i <= n; i++) {
            max = Math.max(max, prefixSum[i] - prefixSum[i - k]);
        }

        System.out.println(max);
    }

}
