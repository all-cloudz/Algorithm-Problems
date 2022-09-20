package problem_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_11054_Memoization {

    private static int N;
    private static int[] nums;
    private static int[] LIS;
    private static int[] LDS;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        nums = new int[N + 2];
        LIS = new int[N + 2];
        LDS = new int[N + 2];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        fillLIS();
        fillLDS();

        int maxLen = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }

        System.out.println(maxLen);
    }

    private static void fillLIS() {
        for (int i = 1; i <= N; i++) {
            fillLIS(i);
        }
    }

    private static int fillLIS(int cur) {
        if (LIS[cur] != 0) {
            return LIS[cur];
        }

        LIS[cur] = 1;

        for (int i = cur - 1; i >= 1; i--) {
            if (nums[i] < nums[cur]) {
                LIS[cur] = Math.max(LIS[cur], fillLIS(i) + 1);
            }
        }

        return LIS[cur];
    }

    private static void fillLDS() {
        for (int i = N; i >= 1; i--) {
            fillLDS(i);
        }
    }

    private static int fillLDS(int cur) {
        if (LDS[cur] != 0) {
            return LDS[cur];
        }

        LDS[cur] = 1;

        for (int i = cur + 1; i <= N; i++) {
            if (nums[cur] > nums[i]) {
                LDS[cur] = Math.max(LDS[cur], fillLDS(i) + 1);
            }
        }

        return LDS[cur];
    }

}
