package backtracking.problem_d3_9229;

import java.io.*;
import java.util.*;

public class Problem_D3_9229_Sort {
    private static int N;
    private static int M;
    private static int[] weights;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            weights = new int[N];
            max = Integer.MIN_VALUE;

            st = new StringTokenizer(input.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            chooseTwo();

            answer.append(String.format("#%d %d%n", tc, (max == Integer.MIN_VALUE) ? -1 : max));
        }

        System.out.println(answer);
    }

    private static void chooseTwo() {
        Arrays.sort(weights);

        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = weights[left] + weights[right];

            if (sum > M) {
                right--;
            } else {
                left++;
                max = Math.max(max, sum);
            }
        }
    }
}
