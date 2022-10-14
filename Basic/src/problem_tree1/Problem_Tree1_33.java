package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_33 {

    private static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return (this.x == o.x) ? this.y - o.y : this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Point> pointSet = new TreeSet<>();
        while (n-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            pointSet.add(new Point(x, y));
        }

        while (m-- > 0) {
            int targetX = Integer.parseInt(input.readLine());
            Point target = pointSet.ceiling(new Point(targetX, 0));

            if (target == null) {
                answer.append("-1 -1\n");
                continue;
            }

            pointSet.remove(target);
            answer.append(target.x).append(" ").append(target.y).append("\n");
        }

        System.out.println(answer);
    }

}
