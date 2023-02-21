package problem_lv2_159993;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem_Lv2_159993 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};

    private int rowLength;
    private int colLength;
    private int[] start;
    private int[] lever;
    private int[] end;

    public int solution(String[] maps) {
        rowLength = maps.length;
        colLength = maps[0].length();
        char[][] reMaps = new char[rowLength][colLength];

        for (int i = 0; i < maps.length; i++) {
            reMaps[i] = maps[i].toCharArray();
        }

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (reMaps[i][j] == 'S') {
                    start = new int[] { i, j };
                    continue;
                }

                if (reMaps[i][j] == 'L') {
                    lever = new int[] { i, j };
                    continue;
                }

                if (reMaps[i][j] == 'E') {
                    end = new int[] { i, j };
                }
            }
        }

        int startToLever = minMoveTime(reMaps, start, lever);
        if (startToLever == -1) {
            return -1;
        }

        int leverToEnd = minMoveTime(reMaps, lever, end);
        if (leverToEnd == -1) {
            return -1;
        }

        return startToLever + leverToEnd;
    }

    private int minMoveTime(char[][] maps, int[] from, int[] to) {
        Queue<int[]> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[rowLength][colLength];

        points.offer(from);
        discovered[from[0]][from[1]] = true;
        int level = 0;

        while (!points.isEmpty()) {
            for (int size = points.size(), i = 0; i < size; i++) {
                int[] cur = points.poll();

                if (cur[0] == to[0] && cur[1] == to[1]) {
                    return level;
                }

                for (int[] dir : DIRECTIONS) {
                    int nextRow = cur[0] + dir[0];
                    int nextCol = cur[1] + dir[1];

                    if (!isMovable(nextRow, nextCol) || maps[nextRow][nextCol] == 'X') {
                        continue;
                    }

                    if (!discovered[nextRow][nextCol]) {
                        points.offer(new int[] { nextRow, nextCol });
                        discovered[nextRow][nextCol] = true;
                    }
                }
            }

            level++;
        }

        return -1;
    }

    private boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < rowLength && 0 <= nextCol && nextCol < colLength;
    }

    public static void main(String[] args) {
        System.out.println(
                new Problem_Lv2_159993().solution(new String[] { "SOOOL","XXXXO","OOOOO","OXXXX","OOOOE" })
        );
    }

}
