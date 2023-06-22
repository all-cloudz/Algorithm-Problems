package implementation.code_1차원_바람;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Code_1차원_바람 {

    private static int N;
    private static int M;
    private static int[][] buildings;
    private static int Q;
    private static Wind[] winds;

    public static void main(String[] args) throws IOException {
        init();
        simulation();
        printBuildings();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        Q = Integer.parseInt(tokenizer.nextToken());

        buildings = new int[N][M];
        for (int i = 0; i < N; i++) {
            buildings[i] = Stream.of(input.readLine().split(" "))
                                 .mapToInt(Integer::parseInt)
                                 .toArray();
        }

        winds = new Wind[Q];
        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            winds[i] = new Wind(
                    Integer.parseInt(tokenizer.nextToken()) - 1,
                    tokenizer.nextToken()
            );
        }
    }

    private static void simulation() {
        for (Wind wind : winds) {
            moveBuildings(wind);
            spreadWind(wind);
        }
    }

    private static void moveBuildings(Wind wind) {
        if (wind.isLeft()) {
            moveRight(wind.row);
            return;
        }

        moveLeft(wind.row);
    }

    private static void spreadWind(Wind wind) {
        spreadUp(wind);
        spreadDown(wind);
    }

    private static void spreadUp(Wind wind) {
        int row = wind.row;
        String direction = wind.direction;

        while (row > 0) {
            if (!canSpread(row, row - 1)) {
                return;
            }

            direction = changeDirection(direction);
            moveBuildings(new Wind(--row, direction));
        }
    }

    private static void spreadDown(Wind wind) {
        int row = wind.row;
        String direction = wind.direction;

        while (row < N - 1) {
            if (!canSpread(row, row + 1)) {
                return;
            }

            direction = changeDirection(direction);
            moveBuildings(new Wind(++row, direction));
        }
    }

    private static String changeDirection(String direction) {
        return "L".equals(direction) ? "R" : "L";
    }

    private static boolean canSpread(int row, int compared) {
        for (int i = 0; i < M; i++) {
            if (buildings[row][i] == buildings[compared][i]) {
                return true;
            }
        }

        return false;
    }

    private static void moveRight(int row) {
        int col = M - 1;
        int inserting = buildings[row][col];

        while (col > 0) {
            buildings[row][col] = buildings[row][col - 1];
            col--;
        }

        buildings[row][col] = inserting;
    }

    private static void moveLeft(int row) {
        int col = 0;
        int inserting = buildings[row][col];

        while (col < M - 1) {
            buildings[row][col] = buildings[row][col + 1];
            col++;
        }

        buildings[row][col] = inserting;
    }

    private static void printBuildings() {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer.append(buildings[i][j]).append(' ');
            }

            answer.append('\n');
        }

        System.out.println(answer);
    }

    private static class Wind {
        private final int row;
        private final String direction;

        public Wind(int row, String direction) {
            this.row = row;
            this.direction = direction;
        }

        public boolean isLeft() {
            return "L".equals(direction);
        }
    }

}
