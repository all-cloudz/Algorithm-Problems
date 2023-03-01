package math.problem_d5_9843;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_D5_9843_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(input.readLine());
            output.write(String.format("#%d %d%n", tc, findHeight(N)));
        }

        output.flush();
    }

    public static long findHeight(long totalCount) {
        long left = 0;
        long right = (long) Math.sqrt(totalCount) << 2;

        while (left < right) {
            long mid = (right - left >> 1) + left;
            long count = mid * (mid + 1) >> 1;

            if (totalCount == count) {
                return mid;
            }

            if (totalCount > count) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }

}
