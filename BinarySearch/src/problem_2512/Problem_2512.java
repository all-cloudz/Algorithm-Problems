package problem_2512;

import java.util.Scanner;

public class Problem_2512 {

    private static int N;
    private static int[] budget;
    private static int maxBudget;
    private static int M;
    private static int sum;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        maxBudget = Integer.MIN_VALUE;
        N = input.nextInt();
        budget = new int[N];
        for (int i = 0; i < budget.length; i++) {
            budget[i] = input.nextInt();
            sum += budget[i];
            maxBudget = Math.max(maxBudget, budget[i]);
        }

        M = input.nextInt();
        System.out.println(binarySearch());
    }

    public static int binarySearch() {
        if (sum <= M) {
            return maxBudget; //////// index가 아니라 내용물을 뱉어야함
        }

        int left = 0;
        int right = M;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (isPossible(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

    // current를 예산으로 사용할 수 있다
    private static boolean isPossible(int mid) {
        int current = 0;

        for (int len = budget.length, i = 0; i < len; i++) {
            if (budget[i] > mid) {
                current += mid;
            } else {
                current += budget[i];
            }
        }

        return current <= M;
    }

}
