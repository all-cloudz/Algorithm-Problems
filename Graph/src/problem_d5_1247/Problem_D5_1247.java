package problem_d5_1247;

import java.io.*;
import java.util.*;

public class Problem_D5_1247 {
    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static int distance(Point p1, Point p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
        }
    }

    private static final int INF = 200 * 12;

    private static StringBuilder answer = new StringBuilder();

    private static int N;
    private static Point[] points;
    private static int[][] dists;
    private static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(input.readLine());
            points = new Point[N + 2];
            dists = new int[N + 2][N + 2];
            memo = new int[(1 << N + 2)][N + 2];
            for (int j = 0; j < (1 << N + 2); j++) {
                 Arrays.fill(memo[j], INF);
            }

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            points[1] = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            points[0] = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            for (int j = 2; j < N + 2; j++) {
                points[j] = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            }

            fillDists();
            answer.append(String.format("#%d %d%n", i, findPath(0, 1)));
        }

        System.out.print(answer);
    }

    private static void fillDists() {
        for (int i = 0; i < N + 2; i++) {
            for (int j = i + 1; j < N + 2; j++) {
                dists[i][j] = Point.distance(points[i], points[j]);;
                dists[j][i] = dists[i][j];
            }
        }
    }

    private static int findPath(int visited, int cur) {
        visited |= (1 << cur);

        if (visited == (1 << N + 2) - 2) {
            return (dists[cur][0] == 0) ? INF : dists[cur][0];
        }

        if (memo[visited][cur] != INF) {
            return memo[visited][cur];
        }

        for (int i = 1; i < N + 2; i++) {
            if ((visited & (1 << i)) == 0 && dists[cur][i] != 0) {
                memo[visited][cur] = Math.min(memo[visited][cur], findPath(visited, i) + dists[cur][i]);
            }
        }

        return memo[visited][cur];
    }
}
