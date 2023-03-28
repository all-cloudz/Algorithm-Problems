package problem_d5_9999;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem_D5_9999 {

    private static int N;
    private static Time[] times;
    private static int[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader input = new BufferedReader(new FileReader(Problem_D5_9999.class.getResource(".").getPath() + "/input.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int L = Integer.parseInt(input.readLine());
            N = Integer.parseInt(input.readLine());
            times = new Time[N + 1];
            prefixSum = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                int start = Integer.parseInt(tokenizer.nextToken());
                int end = Integer.parseInt(tokenizer.nextToken());
                times[i] = new Time(start, end);
                prefixSum[i] = prefixSum[i - 1] + (end - start);
            }

            int maxTime = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                int target = times[i].start + L;
                int upperIdx = binarySearchForUpperBound(target);

                int time = prefixSum[upperIdx - 1] - prefixSum[i - 1];
                if (upperIdx <= N && times[upperIdx].start < target) {
                    time += target - times[upperIdx].start;
                }

                maxTime = Math.max(maxTime, time);
            }

            writer.write(String.format("#%d %d%n", tc, maxTime));
        }

        writer.flush();
    }

    private static int binarySearchForUpperBound(int target) {
        int left = 0;
        int right = N + 1;

        while (left < right) {
            int mid = left + (right - left >> 1);

            if (times[mid].end > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private static class Time {
        private int start;
        private int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
