package math.problem_d4_14450;

import java.io.*;
import java.util.*;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Problem_D4_14450 {
    private static StringBuilder answer = new StringBuilder();
    private static int[] range;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 1; i <= T; i++) {
            answer.append("#").append(i).append(' ');

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            range = new int[2];
            range[0] = Integer.parseInt(tokenizer.nextToken());
            range[1] = Integer.parseInt(tokenizer.nextToken());

            int Q = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(input.readLine());
            while (Q-- > 0) {
                int test = Integer.parseInt(tokenizer.nextToken());
                if (isPossible(test)) {
                    answer.append("O");
                    continue;
                }

                answer.append("X");
            }

            answer.append('\n');
        }

        System.out.print(answer);
    }

    private static boolean isPossible(long test) {
        if (test > range[1]) {
            return false;
        }

        int cover = (int) pow(10, (int) log10(range[0]) - (int) log10(test));
        if (cover > 0) {
            test *= cover;
        }

        while (test <= range[1]) {
            if (inRange(test, cover)) {
                return true;
            }

            test *= 10;
        }

        return false;
    }

    private static boolean inRange(long test, int cover) {
        if (test >= range[0]) {
            return true;
        }

        if (range[0] - test < cover) {
            return true;
        }

        return false;
    }
}
