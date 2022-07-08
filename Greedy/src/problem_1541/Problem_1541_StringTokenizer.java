package problem_1541;

import java.io.*;
import java.util.*;

public class Problem_1541_StringTokenizer {
    private static List<Integer> nums = new ArrayList<>(70);

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine(), "+-", true);

        int sum = 0;
        while (tokenizer.hasMoreTokens()) {
            String cur = tokenizer.nextToken();

            if (cur.equals("+")) {
                continue;
            }

            if (cur.equals("-")) {
                nums.add(sum);
                sum = 0;
                continue;
            }

            sum += Integer.parseInt(cur);
        }
        nums.add(sum);

        int answer = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            answer -= nums.get(i);
        }

        System.out.print(answer);
    }
}

