package problem_1300;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Problem_1300_Sol2 {
    private static long N;
    private static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Long.parseLong(input.readLine());
        K = Long.parseLong(input.readLine());

        long answer = binarySearch();
        output.write(String.valueOf(answer));
        output.flush();

        input.close();
        output.close();
    }

    private static long binarySearch() {
        long left = 1;
        long right = N * N + 1; // UpperBound를 구해야 하는 문제일 경우 답이 N * N이 될 수 있기 때문에 right는 항상 + 1을 해주는 것이 좋다. 그리고 필요하다면 right - 1을 반환하면 된다.
        long mid = 0;

        while (left < right) {
            mid = left + (right - left) / 2;

            if (isLowerBound(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private static boolean isLowerBound(long mid) {
        long cnt = 0;

        for (int i = 1; i <= N; i++) {
            cnt += Math.min(mid / i, N);
        }

        if (cnt >= K) {
            return true;
        }
        return false;
    }
}
