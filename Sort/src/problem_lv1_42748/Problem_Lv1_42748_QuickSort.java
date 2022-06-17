package problem_lv1_42748;

import java.util.Arrays;

public class Problem_Lv1_42748_QuickSort {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int from = commands[i][0] - 1;
            int to = commands[i][1] - 1;
            int pos = commands[i][2] - 1;

            int[] nums = arraycopy(array, from, to);
            quickSort(nums);
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

    private static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(nums, left, right);

        quickSort(nums, left, pivotPos - 1);
        quickSort(nums, pivotPos + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        swap(nums, left, (int) (Math.random() * (right - left + 1)) + left);

        int pivotPos = left;
        int pivot = nums[pivotPos];

        while (left < right) {
            while (left < right && pivot < nums[right]) {
                right--;
            }

            while (left < right && pivot >= nums[left]) {
                left++;
            }

            swap(nums, left, right);
        }

        swap(nums, left, pivotPos);

        return left;
    }

    private static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
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
