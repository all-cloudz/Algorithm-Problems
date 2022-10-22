package prefixSum.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_4 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        int[] prefixSum = new int[N + 1];
        while (B-- > 0) {
            prefixSum[Integer.parseInt(input.readLine())] = 1;
        }

        for (int i = 1; i <= N; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        int minCnt = Integer.MAX_VALUE;
        for (int i = K; i <= N; i++) {
            minCnt = Math.min(minCnt, prefixSum[i] - prefixSum[i - K]);
        }

        System.out.println(minCnt);
    }

}
