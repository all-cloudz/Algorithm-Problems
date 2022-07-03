package problem_d6_14454;

import java.io.*;
import java.util.*;

// 오답, 못풀었음
public class Problem_D6_14454 {
    private static StringBuilder answer = new StringBuilder();

    private static String[] E;
    private static int N;
    private static double[][] ranges;
    private static double S;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(input.readLine());
            ranges = new double[N][2];

            E = input.readLine().split("[+]");
            for (int j = 0; j < N; j++) {
                ranges[j] = Arrays.stream(input.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            }

            S = Double.parseDouble(input.readLine());

            answer.append(String.format("#%d %f%n", i, max()));
        }

        System.out.print(answer);
    }

    private static double max() {
        double max = Double.MIN_VALUE;

        double[] nums = new double[N];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            double target = S / (N - i);

            cut(nums, visited, target);
            fill(nums, visited, target);

            if (sum(nums) > S) {
                continue;
            }

            max = Math.max(max, calulate(nums));
        }

        return max;
    }

    private static void cut(double[] nums, boolean[] visited, double target) {
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            if (ranges[i][1] < target) {
                nums[i] = 0;
                visited[i] = true;
            }
        }
    }

    private static void fill(double[] nums, boolean[] visited, double target) {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                nums[i] = target;
            }
        }
    }

    private static double sum(double[] nums) {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }

        return sum;
    }

    private static double calulate(double[] nums) {
        double sum = 0;

        for (int i = 0; i < E.length; i++) {
            sum += nums[E[i].charAt(0) - 'a'] * nums[E[i].charAt(1) - 'a'];
        }

        return sum;
    }
}
