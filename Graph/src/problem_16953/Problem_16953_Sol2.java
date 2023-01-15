package problem_16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_16953_Sol2 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        long A = Long.parseLong(tokenizer.nextToken());
        long B = Long.parseLong(tokenizer.nextToken());

        System.out.println(convertBToA(B, A));
    }

    private static int convertBToA(long B, long A) {
        Queue<Long> nums = new ArrayDeque<>();

        nums.offer(B);
        int level = 0;

        while (!nums.isEmpty()) {
            for (int size = nums.size(), i = 0; i < size; i++) {
                long cur = nums.poll();

                if (cur == A) {
                    return level + 1;
                }

                if ((cur & 1) == 0 && (cur >> 1) >= A) {
                    nums.offer(cur >> 1);
                    continue;
                }

                if (cur % 10 == 1) {
                    nums.offer(cur / 10);
                }
            }

            level++;
        }

        return -1;
    }

}
