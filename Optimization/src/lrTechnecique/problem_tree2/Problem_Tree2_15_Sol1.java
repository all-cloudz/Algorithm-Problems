package lrTechnecique.problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_Tree2_15_Sol1 {

    private static int N;
    private static int[] nums;
    private static int[] leftMax;
    private static int[] rightMax;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N + 2];
        leftMax = new int[N + 2];
        rightMax = new int[N + 2];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
        }

        for (int i = N; i >= 1; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        int maxVal = Integer.MIN_VALUE;
        for (int i = 1; i <= N - 4; i++) {
            maxVal = Math.max(maxVal, leftMax[i] + nums[i + 2] + rightMax[i + 4]);
        }

        System.out.println(maxVal);
    }

}
