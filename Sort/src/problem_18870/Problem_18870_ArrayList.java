package problem_18870;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Problem_18870_ArrayList {
    private static int[] sorted;

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

        mergeSort(nums);

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
