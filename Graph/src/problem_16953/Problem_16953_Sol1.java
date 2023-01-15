package problem_16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_16953_Sol1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        long A = Long.parseLong(tokenizer.nextToken());
        long B = Long.parseLong(tokenizer.nextToken());

        System.out.println(convertAToB(A, B));

    }

    private static int convertAToB(long A, long B) {
        Queue<Long> nums = new ArrayDeque<>();
        Set<Long> discovered = new HashSet<>();

        nums.offer(A);
        discovered.add(A);
        int level = 0;

        while (!nums.isEmpty()) {
            for (int size = nums.size(), i = 0; i < size; i++) {
                long cur = nums.poll();

                if (cur == B) {
                    return level + 1;
                }

                if (cur > B) {
                    continue;
                }

                long next = cur << 1;
                if (!discovered.contains(next)) {
                    nums.offer(next);
                    discovered.add(next);
                }

                next = 10 * cur + 1;
                if (!discovered.contains(next)) {
                    nums.offer(next);
                    discovered.add(next);
                }
            }

            level++;
        }

        return -1;
    }

}
