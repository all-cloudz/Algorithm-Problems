package problem_3273;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem_3273_QuickSort {
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        int[] nums = new int[n];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        sort(nums);

        int x = Integer.parseInt(input.readLine());

        System.out.print(countPair(nums, x));
    }

    private static int countPair(int[] nums, int x) {
        int head = 0;
        int rear = nums.length - 1;

        while (head < rear) {
            int sum = nums[head] + nums[rear];

            if (sum > x) {
                rear--;
                continue;
            }

            if (sum < x) {
                head++;
                continue;
            }

            answer++;
            head++;
            rear--;
        }

        return answer;
    }

    private static void sort(int[] nums) {
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

        swap(nums, pivotPos, left);

        return left;
    }

    private static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
