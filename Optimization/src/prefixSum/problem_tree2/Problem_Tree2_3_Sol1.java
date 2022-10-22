package prefixSum.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_3_Sol1 {

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

        int cnt = 0;
        for (int right = 1; right <= n; right++) {
            cnt += countOfEqualK(prefixSum, right, k);
        }

        System.out.println(cnt);
    }

    private static int countOfEqualK(int[] prefixSum, int right, int k) {
        int cnt = 0;

        for (int left = 0; left < right; left++) {
            if (prefixSum[right] - prefixSum[left] == k) {
                cnt++;
            }
        }

        return cnt;
    }

}
