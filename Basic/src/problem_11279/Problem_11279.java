package problem_11279;

import java.io.*;
import java.util.*;

public class Problem_11279 {
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        PriorityQueue<Integer> nums = new PriorityQueue<>((a, b) -> b - a);
        // PriorityQueue<Integer> nums = new PriorityQueue<>(Collections.reverseOrder())와 동일한 코드
        while (N-- > 0) {
            int x = Integer.parseInt(input.readLine());

            if (x == 0 && nums.isEmpty()) {
                answer.append(0).append('\n');
                continue;
            }

            if (x == 0) {
                answer.append(nums.poll()).append('\n');
                continue;
            }

            nums.offer(x);
        }

        System.out.print(answer);
    }
}
