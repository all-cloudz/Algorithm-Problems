package problem_chap03_2;

import java.io.*;
import java.util.*;

public class Problem_Chap03_2 {
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int N = Integer.parseInt(tokenizer.nextToken());
        int[] nums = new int[N];

        final int M = Integer.parseInt(tokenizer.nextToken());
        final int K = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(nums);

        int max = nums[N - 1];
        int secondMax = nums[N - 2];

        // 주기가 K + 1인 수열의 합
        answer = (max * K + secondMax) * M / (K + 1) + max * (M % (K + 1));

        System.out.print(answer);
    }
}
