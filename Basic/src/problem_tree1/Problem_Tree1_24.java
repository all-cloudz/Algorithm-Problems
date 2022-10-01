package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_24 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(input.readLine());
        TreeSet<Integer> treeSet = new TreeSet<>();

        while (N-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            String operator = tokenizer.nextToken();
            if ("add".equals(operator)) {
                treeSet.add(Integer.parseInt(tokenizer.nextToken()));
                continue;
            }

            if ("remove".equals(operator)) {
                treeSet.remove(Integer.parseInt(tokenizer.nextToken()));
                continue;
            }

            if ("find".equals(operator)) {
                answer.append(treeSet.contains(Integer.parseInt(tokenizer.nextToken()))).append("\n");
                continue;
            }

            if (treeSet.isEmpty()) {
                answer.append("None\n");
                continue;
            }

            if ("lower_bound".equals(operator)) {
                Integer lbd = treeSet.ceiling(Integer.parseInt(tokenizer.nextToken()));
                answer.append((lbd == null) ? "None" : lbd).append("\n");
                continue;
            }

            if ("upper_bound".equals(operator)) {
                Integer ubd = treeSet.higher(Integer.parseInt(tokenizer.nextToken()));
                answer.append((ubd == null) ? "None" : ubd).append("\n");
                continue;
            }

            if ("largest".equals(operator)) {
                answer.append(treeSet.last()).append("\n");
                continue;
            }

            if ("smallest".equals(operator)) {
                answer.append(treeSet.first()).append("\n");
            }
        }

        System.out.println(answer);
    }

}
