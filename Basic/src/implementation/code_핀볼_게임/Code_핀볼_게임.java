package implementation.code_핀볼_게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code_핀볼_게임 {

    private enum Direction {
        LEFT(new int[] { 0, -1 }) {
            @Override
            public Direction reflect(int mirror) {
                if (mirror == 1) {
                    return DOWN;
                }

                return UP;
            }
        },
        RIGHT(new int[] { 0, 1 }) {
            @Override
            public Direction reflect(int mirror) {
                if (mirror == 1) {
                    return UP;
                }

                return DOWN;
            }
        },
        UP(new int[] { -1, 0 }) {
            @Override
            public Direction reflect(int mirror) {
                if (mirror == 1) {
                    return RIGHT;
                }

                return LEFT;
            }
        },
        DOWN(new int[] { 1, 0 }) {
            @Override
            public Direction reflect(int mirror) {
                if (mirror == 1) {
                    return LEFT;
                }

                return RIGHT;
            }
        };

        private final int[] value;

        Direction(int[] value) {
            this.value = value;
        }

        public int getRow() {
            return value[0];
        }

        public int getCol() {
            return value[1];
        }

        public abstract Direction reflect(int mirror);
    }

    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        init();
        int maxCount = findMaxCount();
        System.out.println(maxCount);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(input.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }
    }

    private static int findMaxCount() {
        return makeStartBalls()
                .stream()
                .mapToInt(Code_핀볼_게임::move)
                .max()
                .getAsInt();
    }

    private static List<Ball> makeStartBalls() {
        List<Ball> startPoints = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            startPoints.add(new Ball(0, i, Direction.DOWN));
            startPoints.add(new Ball(i, 0, Direction.RIGHT));
            startPoints.add(new Ball(N - 1, i, Direction.UP));
            startPoints.add(new Ball(i, N - 1, Direction.LEFT));
        }

        return startPoints;
    }

    private static int move(Ball ball) {
        int stepCount = 1;

        while (inRange(ball.row, ball.col)) {
            ball.move(board);
            stepCount++;
        }

        return stepCount;
    }

    private static boolean inRange(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < N;
    }

    private static class Ball {
        private int row;
        private int col;
        private Direction dir;

        public Ball(int row, int col, Direction dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        public void move(int[][] board) {
            if (board[row][col] != 0) {
                dir = dir.reflect(board[row][col]);
            }

            row = row + dir.getRow();
            col = col + dir.getCol();
        }
    }
}
