package problem_10026;

import java.io.*;
import java.util.*;

public class Problem_10026 {
    private static int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int N;
    private static char[][] picture;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        picture = new char[N][N];
        for (int i = 0; i < N; i++) {
            picture[i] = input.readLine().toCharArray();
        }

        int cntOfNormal = getAreaCntOfNormal();
        int cntOfAbnormal = getAreaCntOfAbnormal();

        System.out.println(cntOfNormal + " " + cntOfAbnormal);
    }

    private static int getAreaCntOfNormal() {
        int cntOfNormal = 0;
        boolean[][] discovered = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!discovered[i][j]) {
                    searchArea(discovered, new int[]{ i, j });
                    cntOfNormal++;
                }
            }
        }

        return cntOfNormal;
    }

    private static int getAreaCntOfAbnormal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (picture[i][j] == 'G') {
                    picture[i][j] = 'R';
                }
            }
        }

        int cntOfAbnormal = 0;
        boolean[][] discovered = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!discovered[i][j]) {
                    searchArea(discovered, new int[]{ i, j });
                    cntOfAbnormal++;
                }
            }
        }

        return cntOfAbnormal;
    }

    private static void searchArea(boolean[][] discovered, int[] start) {
        Stack<int[]> points = new Stack<>();

        points.push(start);
        discovered[start[0]][start[1]] = true;
        char colorOfArea = picture[start[0]][start[1]];

        while (!points.isEmpty()) {
            int[] cur = points.pop();

            for (int[] move : DIRECTIONS) {
                int nextRow = cur[0] + move[0];
                int nextCol = cur[1] + move[1];

                if (!isMovable(nextRow, nextCol)) {
                    continue;
                }

                if (colorOfArea != picture[nextRow][nextCol]) {
                    continue;
                }

                if (!discovered[nextRow][nextCol]) {
                    points.push(new int[] { nextRow, nextCol });
                    discovered[nextRow][nextCol] = true;
                }
            }
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
    }
}
