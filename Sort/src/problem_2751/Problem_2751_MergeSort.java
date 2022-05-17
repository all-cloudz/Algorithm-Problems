package problem_2751;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_2751_MergeSort {
    private static StringBuilder answer = new StringBuilder();
    private static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        mergeSort(nums);

        for (int i = 0; i < N; i++) {
            answer.append(nums[i]).append('\n');
        }

        System.out.print(answer);
    }

    private static void mergeSort(int[] nums) {
        sorted = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        sorted = null;
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left >> 1);

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            sorted[idx++] = (nums[l] <= nums[r]) ? nums[l++] : nums[r++];
        }

        if (l <= mid) {
            System.arraycopy(nums, l, sorted, idx, mid - l + 1);
        } else {
            System.arraycopy(nums, r, sorted, idx, right - r + 1);
        }

        System.arraycopy(sorted, left, nums, left, right - left + 1);
    }
}
