package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_25 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            int K = Integer.parseInt(input.readLine());

            while (K-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                String operater = tokenizer.nextToken();

                if ("I".equals(operater)) {
                    treeSet.add(Integer.parseInt(tokenizer.nextToken()));
                    continue;
                }

                if (treeSet.isEmpty()) {
                    continue;
                }

                if ("1".equals(tokenizer.nextToken())) {
                    treeSet.pollLast();
                    continue;
                }

                treeSet.pollFirst();
            }

            if (treeSet.isEmpty()) {
                answer.append("EMPTY\n");
                continue;
            }

            answer.append(treeSet.last()).append(" ").append(treeSet.first()).append("\n");
        }

        System.out.println(answer);
    }

}
