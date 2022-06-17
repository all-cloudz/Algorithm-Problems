package problem_lv1_42748;

import java.util.Arrays;

public class Problem_Lv1_42748_MergeSort {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int from = commands[i][0] - 1;
            int to = commands[i][1] - 1;
            int pos = commands[i][2] - 1;

            int[] nums = arraycopy(array, from, to);
            mergeSort(nums);
            answer[i] = nums[pos];
        }

        return answer;
    }

    private static int[] arraycopy(int[] array, int from, int to) {
        int[] nums = new int[to - from + 1];
        for (int j = from; j <= to; j++) {
            nums[j - from] = array[j];
        }

        return nums;
    }

    private static void mergeSort(int[] nums) {
        int[] sorted = new int[nums.length];
        mergeSort(nums, sorted, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int[] sorted, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left >> 1);

        mergeSort(nums, sorted, left, mid);
        mergeSort(nums, sorted, mid + 1, right);

        merge(nums, sorted, left, right);
    }

    private static void merge(int[] nums, int[] sorted, int left, int right) {
        int idx = left;
        int mid = left + (right - left >> 1);
        int l = left;
        int r = mid + 1;

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

    public static void main(String[] args) {
        int[] array = new int[] {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = new int[][] {
                new int[] {2, 5, 3},
                new int[] {4, 4, 1},
                new int[] {1, 7, 3}
        };

        int[] answer = solution(array, commands);
        System.out.print(Arrays.toString(answer));
    }
}
