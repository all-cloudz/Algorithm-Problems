package problem_11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_11053 {

    private static int N;
    private static int[] nums;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N];
        cache = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println(tabulate());
    }

    private static int tabulate() {
        cache[0] = nums[0];
        int size = 1;

        for (int i = 1; i < N; i++) {
            if (cache[size - 1] < nums[i]) {
                cache[size++] = nums[i];
            }

            int insertedIdx = binarySearch_LBD(0, size, nums[i]);
            cache[insertedIdx] = nums[i];
        }

        return size;
    }

    private static int binarySearch_LBD(int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left >> 1);

            if (cache[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

}
