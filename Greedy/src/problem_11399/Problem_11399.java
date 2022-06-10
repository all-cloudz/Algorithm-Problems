package problem_11399;

import java.io.*;
import java.util.*;

public class Problem_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        int[] times = new int[N + 1];
        int[] cumulativeSum = new int[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            times[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(times);

        for (int i = 1; i <= N; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + times[i];
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer += cumulativeSum[i];
        }

        System.out.print(answer);
    }
}
