package problem_chap04_3;

import java.io.*;
import java.util.*;

public class Problem_Chap04_3 {
    private static class Player {
        private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        private int[] coordinate;
        private int direction;
        private boolean[][] visited;

        public Player(int row, int col, int direction, final int N, final int M) {
            this.coordinate = new int[] {row, col};
            this.direction = direction;
            this.visited = new boolean[N][M];
            this.visited[row][col] = true;
        }

        public boolean isMovable(int[][] map) {
            for (int i = 1; i <= moves.length; i++) {
                direction = (direction + 3) % 4;

                int moveRow = coordinate[0] + moves[direction][0];
                int moveCol = coordinate[1] + moves[direction][1];

                if (!visited[moveRow][moveCol] && map[moveRow][moveCol] == 0) {
                    return true;
                }
            }

            return false;
        }

        public void move(int[][] map) {
            coordinate[0] += moves[direction][0];
            coordinate[1] += moves[direction][1];
            visited[coordinate[0]][coordinate[1]] = true;
            answer++;
        }

        public boolean isMovableBack(int[][] map) {
            int moveRow = coordinate[0] - moves[direction][0];
            int moveCol = coordinate[1] - moves[direction][1];

            return map[moveRow][moveCol] == 0;
        }

        public void moveBack(int[][] map) {
            coordinate[0] -= moves[direction][0];
            coordinate[1] -= moves[direction][1];
            answer++;
        }
    }

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int N = Integer.parseInt(tokenizer.nextToken());
        final int M = Integer.parseInt(tokenizer.nextToken());
        int[][] map = new int[N][M];

        tokenizer = new StringTokenizer(input.readLine());
        Player player = new Player(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), N, M);

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

       while (true) {
            if (player.isMovable(map)) {
                player.move(map);
                continue;
            }

            if (player.isMovableBack(map)) {
                player.moveBack(map);
                continue;
            }

            break;
        }

        System.out.print(answer);
    }
}
