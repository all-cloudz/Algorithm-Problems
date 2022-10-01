package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_27 {

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
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Point> pointSet = new TreeSet<>();
        while (N-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            pointSet.add(new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        while (M-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            Point friend = pointSet.higher(new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));

            if (friend == null) {
                answer.append("-1 -1\n");
                continue;
            }

            answer.append(friend.x).append(" ").append(friend.y).append("\n");
        }

        System.out.println(answer);
    }

}
