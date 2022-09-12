package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_Tree1_16 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Set<Integer> numSet = new HashSet<>();
        int n = Integer.parseInt(input.readLine());
        while (n-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            String operator = tokenizer.nextToken();
            int num = Integer.parseInt(tokenizer.nextToken());

            if (operator.equals("find")) {
                answer.append(numSet.contains(num)).append('\n');
            } else if (operator.equals("add")) {
                numSet.add(num);
            } else {
                numSet.remove(num);
            }
        }

        System.out.println(answer);
    }

}
