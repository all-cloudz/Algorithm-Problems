package problem_1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_1021 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int min = 0;

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Queue<Integer> nums = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            nums.offer(i);
        }

        tokenizer = new StringTokenizer(input.readLine());
        while (M-- > 0) {
            int cnt = 0;
            int pick = Integer.parseInt(tokenizer.nextToken());

            while (!nums.isEmpty() && nums.peek() == pick) {
                nums.offer(nums.poll());
                cnt++;
            }

            min += Math.min(cnt, nums.size() - cnt);
            nums.poll();
        }

        System.out.println(min);
    }

}
