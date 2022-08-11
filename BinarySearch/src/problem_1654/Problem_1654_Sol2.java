package problem_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1654_Sol2 {
    private static int K;
    private static int N;
    private static long[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lines = new long[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(input.readLine());
        }

        System.out.println(binarySearch_UBD());
    }

    private static long binarySearch_UBD() {
        long left = 0;
        long right = Integer.MIN_VALUE;

        for (long cur : lines) {
            right = Math.max(right, cur + 1);
        }

        while (left < right) {
            long mid = left + (right - left >> 1);

            if (isPossible(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right - 1;
    }

    private static boolean isPossible(long mid) {
        int cnt = 0;

        for (long cur : lines) {
            cnt += cur / mid;
        }

        return cnt >= N;
    }
}
