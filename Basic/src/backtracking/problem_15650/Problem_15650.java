package backtracking.problem_15650;

import java.util.Scanner;

public class Problem_15650 {
    private static final StringBuilder answer = new StringBuilder();

    private static int N;
    private static int M;
    private static int[] nums;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        nums = new int[M];

        backtracking(1, 0);
        System.out.print(answer);
    }

    private static void backtracking(int start, int depth) {
        if (depth == M) {
            for (int val : nums) {
                answer.append(val).append(' ');
            }

            answer.append('\n');
            return;
        }

        for (int i = start; i <= N; i++) { // 증가하는 수열이므로 15649번과 달리 방문 여부를 기록할 필요가 없다.
            nums[depth] = i;
            backtracking(i + 1, depth + 1);
        }
    }
}
