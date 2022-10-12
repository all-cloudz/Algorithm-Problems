package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_28 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> numSet = new TreeSet<>();
        for (int i = 1; i <= m; i++) {
            numSet.add(i);
        }

        tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            numSet.remove(Integer.parseInt(tokenizer.nextToken()));
            answer.append(numSet.last()).append("\n");
        }

        System.out.println(answer);
    }

}
