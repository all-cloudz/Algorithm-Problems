package problem_10816;

import java.util.Scanner;
import java.util.Arrays;

public class Problem_10816_Sol2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);

        int M = scanner.nextInt();

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int target = scanner.nextInt();

            int sup = binarySearch_UpperBound(nums, 0, nums.length, target);
            int inf = binarySearch_LowerBound(nums, 0, nums.length, target);

            answer.append(sup - inf).append(' ');
        }

        System.out.println(answer);
    }

    // LowerBound는 조건을 만족시키는 값 중 최소인 경우
    private static int binarySearch_LowerBound(int[] nums, int left, int right, int target) {
        int mid = 0;

        while (left < right) {
            mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    // UpperBound는 조건을 만족시키는 값 중 최대인 경우 (정확히는 찾고자 하는 최대인 경우보다 index가 하나 더 크다)
    private static int binarySearch_UpperBound(int[] nums, int left, int right, int target) {
        int mid = 0;

        while (left < right) {
            mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right; // right - 1이면 정확하게 조건을 만족시키는 값 중 최대인 경우가 된다
    }
}
