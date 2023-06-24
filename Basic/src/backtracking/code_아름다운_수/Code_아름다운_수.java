package backtracking.code_아름다운_수;

import java.util.Scanner;

public class Code_아름다운_수 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        System.out.println(countBeautifulNumber(n, nums, 0));
    }

    private static int countBeautifulNumber(int n, int[] nums, int idx) {
        if (idx == n) {
            return isBeautifulNumber(nums) ? 1 : 0;
        }

        int count = 0;

        for (int i = 1; i <= 4; i++) {
            nums[idx] = i;
            count += countBeautifulNumber(n, nums, idx + 1);
        }

        return count;
    }

    private static boolean isBeautifulNumber(int[] nums) {
        int count = 1;

        for (int length = nums.length, i = 1; i < length; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
                continue;
            }

            if (count % nums[i - 1] != 0) {
                return false;
            }

            count = 1;
        }

        return count % nums[nums.length - 1] == 0;
    }

}
