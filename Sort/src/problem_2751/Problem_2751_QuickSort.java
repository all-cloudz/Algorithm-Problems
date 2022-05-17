package problem_2751;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_2751_QuickSort {
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        quickSort(nums);

        for (int i = 0; i < N; i++) {
            answer.append(nums[i]).append('\n');
        }

        System.out.print(answer);
    }

    // 일반적인 퀵 정렬은 최악 시간복잡도가 O(n^2)이므로 시간 초과 발생!!
    // 호어 분할법 + 난수 분할법
    private static void quickSort(int[] nums) {
        quickSort_recursive(nums, 0, nums.length - 1);
    }

    private static void quickSort_recursive(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(nums, left, right);

        quickSort_recursive(nums, left, pivotPos - 1);
        quickSort_recursive(nums, pivotPos + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        swap(nums, (int) (Math.random() * (right - left + 1) + left), left);
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

        swap(nums, pivotPos, left);

        return left;
    }

    private static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
