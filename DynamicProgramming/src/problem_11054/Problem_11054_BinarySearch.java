package problem_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_11054_BinarySearch {

    private static int N;
    private static int[] nums;
    private static int[] numsReverse;

    private static int[] LIS;
    private static int[] LISReverse;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N];
        numsReverse = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
            numsReverse[N - 1 - i] = nums[i];
        }

        LIS = new int[N];
        fillLIS(nums, LIS);

        LISReverse = new int[N];
        fillLIS(numsReverse, LISReverse);

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LISReverse[N - 1 - i] - 1);
        }

        System.out.println(maxLen);
    }

    public static void fillLIS(int[] nums, int[] LIS) {
        int[] cache = new int[N];

        cache[0] = nums[0];
        LIS[0] = 1;
        int lastIndex = 1;

        for (int i = 1; i < N; i++) {
            if (cache[lastIndex - 1] < nums[i]) {
                cache[lastIndex] = nums[i];
                LIS[i] = ++lastIndex;
                continue;
            }

            int index = binarySearch_LBD(cache, 0, lastIndex, nums[i]);
            cache[index] = nums[i];
            LIS[i] = index + 1;
        }
    }

    private static int binarySearch_LBD(int[] cache, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left >> 1);
            
            if (cache[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return right;
    }

}
