package twopointer.problem_d5_10507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D5_10507 {

    private static int n;
    private static int p;
    private static int[] studied;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            p = Integer.parseInt(tokenizer.nextToken());

            studied = new int[n];
            tokenizer = new StringTokenizer(input.readLine());
            for (int i = 0; i < n; i++) {
                studied[i] = Integer.parseInt(tokenizer.nextToken());
            }

            answer.append(String.format("#%d %d%n", tc, maxLength()));
        }

        System.out.println(answer);
    }

    private static int maxLength() {
        int index = 0;
        int left = studied[index] - p;
        int right = studied[index];
        int maxLength = p;

        while (left <= studied[n - 1]) {
           if (left == studied[index]) {
                index++;
            } else {
                right++;
            }

            if (right != studied[index]) {
                left++;
            }

            maxLength = Math.max(maxLength, right - left);
        }

        return Math.max(maxLength, right - left);
    }

//    private static int initRight() {
//        int index = 0;
//        int right = studied[index];
//        int count = p;
//
//        while (count > 0) {
//            if (index < n && right == studied[index]) {
//                index++;
//            } else {
//                count--;
//            }
//
//            right++;
//        }
//
//        return right;
//    }

}
