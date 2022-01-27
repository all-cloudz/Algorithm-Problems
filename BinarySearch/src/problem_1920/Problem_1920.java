package problem_1920;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Problem_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st1.nextToken());
        }

        Arrays.sort(nums);

        final int M = Integer.parseInt(br.readLine());
        int[] checks = new int[M];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            checks[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < checks.length; i++) {
            if (binarySearch(nums, 0, nums.length - 1, checks[i])) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean binarySearch(int[] nums, int left, int right, int target) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return true;
        } else if (left >= right){
            return false;
        }

        if (nums[mid] > target) {
            return binarySearch(nums, left, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, right, target);
        }
    }
}
