package problem_3273;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem_3273_MergeSort {
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
        int[] sorted = new int[nums.length];
        mergeSort(nums, sorted, 0, nums.length - 1);
        sorted = null;
    }

    private static void mergeSort(int[] nums, int[] sorted, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left >> 1);

        mergeSort(nums, sorted, left, mid);
        mergeSort(nums, sorted, mid + 1, right);

        merge(nums, sorted, left, mid, right);
    }

    private static void merge(int[] nums, int[] sorted, int left, int mid, int right) {
        int leftIdx = left;
        int rightIdx = mid + 1;
        int sortedIdx = left;

        while (leftIdx <= mid && rightIdx <= right) {
            if (nums[leftIdx] <= nums[rightIdx]) {
                sorted[sortedIdx++] = nums[leftIdx++];
                continue;
            }

            sorted[sortedIdx++] = nums[rightIdx++];
        }

        if (leftIdx <= mid) {
            System.arraycopy(nums, leftIdx, sorted, sortedIdx, mid - leftIdx + 1);
        }

        if (rightIdx <= right) {
            System.arraycopy(nums, rightIdx, sorted, sortedIdx, right - rightIdx + 1);
        }

        System.arraycopy(sorted, left, nums, left, right - left + 1);
    }
}
