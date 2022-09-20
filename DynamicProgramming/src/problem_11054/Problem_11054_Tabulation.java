package problem_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_11054_Tabulation {

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
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }
    }

    private static void fillLDS() {
        for (int i = N; i >= 1; i--) {
            for (int j = N + 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }
    }

}
