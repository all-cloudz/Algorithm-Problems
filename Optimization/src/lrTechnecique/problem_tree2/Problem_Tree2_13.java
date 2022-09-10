package lrTechnecique.problem_tree2;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Problem_Tree2_13 {

    private static int N;
    private static Point[] checkPoints;
    private static int[] leftSum;
    private static int[] rightSum;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        checkPoints = new Point[N];
        leftSum = new int[N];
        rightSum = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            checkPoints[i] = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        for (int i = 1; i < N; i++) {
            leftSum[i] = leftSum[i - 1] + distance(checkPoints[i], checkPoints[i - 1]);
        }

        for (int i = N - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + distance(checkPoints[i], checkPoints[i + 1]);
        }

        int min = Integer.MAX_VALUE;
        for (int i = N - 2; i > 0; i--) {
            min = Math.min(min, leftSum[i - 1] + rightSum[i + 1] + distance(checkPoints[i - 1], checkPoints[i + 1]));
        }

        System.out.println(min);
    }

    private static int distance (Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

}
