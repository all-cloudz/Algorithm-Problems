package string.problem_단어가_등장하는_횟수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Problem_단어가_등장하는_횟수_TimeOut_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String B = input.readLine();
            String S = input.readLine();

            Map<String, Integer> stringToCount = new HashMap<>();
            for (int lastIdx = B.length() - S.length(), i = 0; i <= lastIdx; i++) {
                String cur = B.substring(i, i + S.length());
                int count = stringToCount.getOrDefault(cur, 0) + 1;
                stringToCount.put(cur, count);
            }

            answer.append(String.format("#%d %d%n", tc, stringToCount.getOrDefault(S, 0)));
        }

        System.out.println(answer);
    }

}
