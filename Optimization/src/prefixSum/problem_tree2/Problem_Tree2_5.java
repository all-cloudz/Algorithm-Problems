package prefixSum.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_5 {

    private static final int MAX = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        int[] prefixSum = new int[MAX];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            prefixSum[Integer.parseInt(tokenizer.nextToken())] = 1;
        }

        for (int i = 1; i < MAX; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        while (Q-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int left = Integer.parseInt(tokenizer.nextToken());
            int right = Integer.parseInt(tokenizer.nextToken());

            answer.append(sum(prefixSum, left, right)).append(System.lineSeparator());
        }

        System.out.println(answer);
    }

    private static int sum(int[] prefixSum, int left, int right) {
        if (left == 0) {
            return prefixSum[right];
        }

        return prefixSum[right] - prefixSum[left - 1];
    }

}
