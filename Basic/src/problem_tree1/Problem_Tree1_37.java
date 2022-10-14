package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_37 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> numSet = new TreeSet<>();
        tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            numSet.add(Integer.parseInt(tokenizer.nextToken()));
        }

        tokenizer = new StringTokenizer(input.readLine());
        while (m-- > 0) {
            int target = Integer.parseInt(tokenizer.nextToken());
            Integer ubd = numSet.floor(target);

            if (ubd == null) {
                answer.append("-1\n");
                continue;
            }

            numSet.remove(ubd);
            answer.append(ubd).append("\n");
        }

        System.out.println(answer);
    }

}
