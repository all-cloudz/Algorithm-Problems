package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_35 {

    private static class Interval implements Comparable<Interval> {
        private int left; // exclusive
        private int right; // exclusive
        private int length;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
            this.length = right - left - 1;
        }

        @Override
        public int compareTo(Interval o) {
            return (this.length == o.length) ? this.left - o.left : o.length - this.length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Integer> numSet = new TreeSet<>();
        numSet.add(-1);
        numSet.add(n + 1);

        TreeSet<Interval> intervalSet = new TreeSet<>();
        intervalSet.add(new Interval(-1, n + 1));

        tokenizer = new StringTokenizer(input.readLine());
        while (m-- > 0) {
            int target = Integer.parseInt(tokenizer.nextToken());
            int right = numSet.higher(target);
            int left = numSet.lower(target);
            intervalSet.remove(new Interval(left, right));

            numSet.add(target);
            intervalSet.add(new Interval(left, target));
            intervalSet.add(new Interval(target, right));

            answer.append(intervalSet.first().length).append("\n");
        }

        System.out.println(answer);
    }

}
