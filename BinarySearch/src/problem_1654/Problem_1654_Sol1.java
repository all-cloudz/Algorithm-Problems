package problem_1654;

import java.io.*;
import java.util.*;

public class Problem_1654_Sol1 {
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static long binarySearch_UBD() {
        long left = 0;
        long right = Arrays.stream(lines).max().getAsLong() + 1;

//        OptionalInt max = Arrays.stream(lines).max();
//        if (max.isPresent()) {
//            int right = max.getAsInt() + 1;
//        }

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
