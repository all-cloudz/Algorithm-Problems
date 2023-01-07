package math.problem_1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1027 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        int[] buildings = new int[N];
        int[] visible = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < N; i++) {
            double slope = Integer.MIN_VALUE;

            for (int j = i + 1; j < N; j++) {
                double targetSlope = (double) (buildings[i] - buildings[j]) / (i - j);

                if (Double.compare(slope, targetSlope) < 0) {
                    visible[i]++;
                    visible[j]++;
                    slope = targetSlope;
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int cur : visible) {
            answer = Math.max(answer, cur);
        }

        System.out.println(answer);
    }
}
