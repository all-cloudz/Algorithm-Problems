package problem_2750;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem_2750 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        insertionSort(nums);

        for (int i = 0; i < N; i++) {
            System.out.println(nums[i]);
        }
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int idx = i;

            while (idx > 0 && nums[idx - 1] > tmp) {
                nums[idx] = nums[idx - 1];
                idx--;
            }

            nums[idx] = tmp;
        }
    }
}
