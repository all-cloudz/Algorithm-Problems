package implementation.problem_10157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_10157 {

    private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private static int R, C;
    private static int[][] seat;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        C = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());
        int target = Integer.parseInt(input.readLine());

        seat = new int[R][C];
        int size = R * C;

        int curRow = 0;
        int curCol = 0;
        int num = 1;
        seat[curRow][curCol] = num;

        if (target > size) {
            System.out.println(0);
        }

        while (num < size) {
            for (int[] move : DIRECTIONS) {
                int nextRow = curRow;
                int nextCol = curCol;

                while (true) {
                    if (num == target) {
                        System.out.println((curCol + 1) + " " + (curRow + 1));
                        System.exit(0);
                    }

                    nextRow += move[0];
                    nextCol += move[1];

                    if (!isMovable(nextRow, nextCol)) {
                        break;
                    }

                    seat[nextRow][nextCol] = ++num;
                    curRow = nextRow;
                    curCol = nextCol;
                }
            }
        }
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
            return false;
        }

        return seat[nextRow][nextCol] == 0;
    }

}
