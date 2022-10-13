package problem_tree1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree1_29 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        TreeSet<Integer> pointSet = new TreeSet<>();
        pointSet.add(0);

        int n = Integer.parseInt(input.readLine());
        int minDist = Integer.MAX_VALUE;

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        while (n-- > 0) {
            int point = Integer.parseInt(tokenizer.nextToken());
            pointSet.add(point);

            Integer higherPoint = pointSet.higher(point);
            Integer lowerPoint = pointSet.lower(point);

            if (higherPoint != null) {
                minDist = Math.min(minDist, higherPoint - point);
            }

            if (lowerPoint != null) {
                minDist = Math.min(minDist, point - lowerPoint);
            }

            answer.append(minDist).append("\n");
        }

        System.out.println(answer);
    }

}
