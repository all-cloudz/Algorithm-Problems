package backtracking.problem_15651;

import java.util.Scanner;

public class Problem_15651 {
    private static final StringBuilder answer = new StringBuilder();

    private static int N;
    private static int M;
    private static int[] nums;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        nums = new int[M];

        backtracking(0);
        System.out.print(answer);
    }

    private static void backtracking(int depth) {
        if (depth == M) {
            for (int val : nums) {
                answer.append(val).append(' ');
            }

            answer.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            nums[depth] = i + 1;
            backtracking(depth + 1);
        }
    }
}