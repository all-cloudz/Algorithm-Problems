package problem_1158;

import java.io.*;
import java.util.*;

public class Problem_1158_Array {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        answer.append("<");

        StringTokenizer st = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<String> nums = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            nums.add(String.valueOf(i));
        }

        List<String> deleted = new ArrayList<>();
        int idx = 0;
        while (!nums.isEmpty()) {
            idx = (idx + K - 1) % N;
            deleted.add(nums.remove(idx));
            N--;
        }

        answer.append(String.join(", ", deleted)).append(">");
        System.out.println(answer);
    }
}
