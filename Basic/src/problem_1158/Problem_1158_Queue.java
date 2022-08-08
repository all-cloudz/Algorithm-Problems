package problem_1158;

import java.io.*;
import java.util.*;

public class Problem_1158_Queue {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        answer.append("<");

        StringTokenizer st = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<String> nums = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            nums.add(String.valueOf(i));
        }

        List<String> deleted = new ArrayList<>();
        int cnt = 0;
        while (!nums.isEmpty()) {
            if (cnt < K - 1) {
                nums.offer(nums.poll());
                cnt++;
                continue;
            }

            deleted.add(nums.poll());
            cnt = 0;
        }

        answer.append(String.join(", ", deleted));

        answer.append(">");
        System.out.println(answer);
    }
}
