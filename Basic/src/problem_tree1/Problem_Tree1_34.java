package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_34 {

    private static class Problem implements Comparable<Problem> {
        private int no;
        private int level;

        public Problem(int no, int level) {
            this.no = no;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            return (this.level == o.level) ? this.no - o.no : this.level - o.level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(input.readLine());
        TreeSet<Problem> problemSet = new TreeSet<>();
        while (n-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int no = Integer.parseInt(tokenizer.nextToken());
            int level = Integer.parseInt(tokenizer.nextToken());
            problemSet.add(new Problem(no, level));
        }

        int m = Integer.parseInt(input.readLine());
        while (m-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            String operator = tokenizer.nextToken();

            if ("rc".equals(operator)) {
                operator = tokenizer.nextToken();
                answer.append(print(problemSet, operator)).append("\n");
                continue;
            }

            int no = Integer.parseInt(tokenizer.nextToken());
            int level = Integer.parseInt(tokenizer.nextToken());

            if ("ad".equals(operator)) {
                problemSet.add(new Problem(no, level));
                continue;
            }

            problemSet.remove(new Problem(no, level));
        }

        System.out.println(answer);
    }

    private static int print(TreeSet<Problem> problemSet, String operator) {
        if ("1".equals(operator)) {
            return problemSet.last().no;
        }

        return problemSet.first().no;
    }

}
