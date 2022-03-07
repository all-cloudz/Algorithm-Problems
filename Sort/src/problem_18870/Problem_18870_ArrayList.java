package problem_18870;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Problem_18870_ArrayList {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];
        int[] copyNums = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            copyNums[i] = nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        motQuickSort(nums); // 이 부분을 Arrays.sort(nums)로 바꾸면 통과...

        ArrayList<Integer> compress = new ArrayList<>();
        compress.add(nums[0]);
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1]) {
                compress.add(nums[i]);
            }
        }

        for (int i = 0; i < N; i++) {
            output.write(binarySearch(compress, copyNums[i]) + " ");
        }

        output.flush();
        output.close();
        input.close();
    }

    private static void motQuickSort(int[] nums) {
        leftPivotSort(nums, 0, nums.length - 1);
    }

    private static void leftPivotSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(nums, left, right);

        leftPivotSort(nums, left, pivotPos - 1);
        leftPivotSort(nums, pivotPos + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivotPos = medianOfThree(nums, left, right);
        int pivot = nums[pivotPos];

        while (left < right) {
            while (pivot < nums[right]) {
                right--;
            }

            while (left < right && pivot >= nums[left]) {
                left++;
            }

            swap(nums, left, right);
        }

        swap(nums, pivotPos, right);

        return right;
    }

    private static int medianOfThree(int[] nums, int left, int right) {
        int mid = left + ((right - left) >> 1);

        if (nums[right] > nums[mid]) {
            swap(nums, right, mid);
        }

        if (nums[right] > nums[left]) {
            swap(nums, right, left);
        }

        if (nums[left] > nums[mid]) {
            swap(nums, left, mid);
        }

        return left;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static int binarySearch(ArrayList<Integer> nums, int target) {
        int left = 0;
        int right = nums.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
