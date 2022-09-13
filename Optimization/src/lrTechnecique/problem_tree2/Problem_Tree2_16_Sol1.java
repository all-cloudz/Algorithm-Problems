package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_16_Sol1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        int[] nums = new int[n + 2];
        int[] leftMax = new int[n + 2];
        int[] rightMax = new int[n + 2];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
        }

        for (int i = n; i >= 1; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        while (q-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int left = Integer.parseInt(tokenizer.nextToken());
            int right = Integer.parseInt(tokenizer.nextToken());

            answer.append(Math.max(leftMax[left - 1], rightMax[right + 1])).append('\n');
        }

        System.out.println(answer);
    }

}
