package problem_11659;

import java.io.*;
import java.util.*;

public class Problem_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        final int M = Integer.parseInt(tokenizer.nextToken());
        int[] cumulativeSum = new int[N + 1];

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            output.write(String.valueOf(cumulativeSum[end] - cumulativeSum[start - 1]));
            output.write('\n');
        }

        output.flush();
    }
}
