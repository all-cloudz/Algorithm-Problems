package problem_9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_9205_FloydWarshall {

    private static int n;
    private static int[][] points;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(input.readLine());
            points = new int[n + 2][2];

            for (int length = n + 2, i = 0; i < length; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                points[i] = new int[] { Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()) };
            }

            boolean[][] canGo = go();

            answer.append(canGo[0][n + 1] ? "happy\n" : "sad\n");
        }

        System.out.println(answer);
    }

    private static boolean[][] go() {
        boolean[][] canGo = new boolean[n + 2][n + 2];

        for (int length = n + 2, i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (distance(points[i], points[j]) <= 1000) {
                    canGo[i][j] = true;
                }
            }
        }

        for (int length = n + 2, wayPoint = 0; wayPoint < length; wayPoint++) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (!canGo[i][j] && canGo[i][wayPoint] && canGo[wayPoint][j]) {
                        canGo[i][j] = true;
                    }
                }
            }
        }

        return canGo;
    }

    private static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

}
