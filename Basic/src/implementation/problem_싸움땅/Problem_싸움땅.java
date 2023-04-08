package implementation.problem_싸움땅;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_싸움땅 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    private static int n;
    private static Point[][] map;
    private static int m;
    private static Player[] players;
    private static int[][] isPlayer;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        map = new Point[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < n; j++) {
                int gun = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = new Point(i, j);

                if (gun > 0) {
                    map[i][j].guns.offer(gun);
                }
            }
        }

        players = new Player[m + 1];
        isPlayer = new int[n][n];
        for (int i = 1; i <= m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            isPlayer[row][col] = i;

            int dir = Integer.parseInt(tokenizer.nextToken());
            int status = Integer.parseInt(tokenizer.nextToken());
            players[i] = new Player(i, map[row][col], dir, status);
        }

        while (k-- > 0) {
            simulate();
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            answer.append(players[i].score).append(' ');
        }
        System.out.println(answer);
    }

    private static void simulate() {
        for (int i = 1; i <= m; i++) {
            Player cur = players[i];
            moveForward(cur);

            if (isPlayer[cur.row()][cur.col()] != 0) {
                fight(cur);
                continue;
            }

            gainGun(cur);
        }
    }

    private static void moveForward(Player player) {
        isPlayer[player.row()][player.col()] = 0;
        int nextRow = player.row() + player.dir()[0];
        int nextCol = player.col() + player.dir()[1];

        if (!canMove(nextRow, nextCol)) {
            player.turnBack();
            nextRow = player.row() + player.dir()[0];
            nextCol = player.col() + player.dir()[1];
        }

        player.moveTo(map[nextRow][nextCol]);
    }

    private static void fight(Player winner) {
        int playerIdx = isPlayer[winner.row()][winner.col()];
        Player loser = players[playerIdx];
        isPlayer[loser.row()][loser.col()] = 0;

        if (winner.power() < loser.power()) {
            Player tmp = winner;
            winner = loser;
            loser = tmp;
        }

        if (winner.power() == loser.power() && winner.status < loser.status) {
            Player tmp = winner;
            winner = loser;
            loser = tmp;
        }

        winner.score += winner.power() - loser.power();
        run(loser);
        gainGun(winner);
    }

    private static void run(Player loser) {
        loser.loseGun();
        int nextRow = loser.row() + loser.dir()[0];
        int nextCol = loser.col() + loser.dir()[1];

        while (!canMove(nextRow, nextCol) || isPlayer[nextRow][nextCol] != 0) {
            loser.turnRight();
            nextRow = loser.row() + loser.dir()[0];
            nextCol = loser.col() + loser.dir()[1];
        }

        loser.moveTo(map[nextRow][nextCol]);
        gainGun(loser);
    }

    private static void gainGun(Player player) {
        isPlayer[player.row()][player.col()] = player.no;
        PriorityQueue<Integer> guns = player.point.guns;
        if (guns.isEmpty()) {
            return;
        }

        if (player.gun == 0) {
            player.gun = guns.poll();
            return;
        }

        if (player.gun < guns.peek()) {
            guns.offer(player.gun);
            player.gainGun(guns.poll());
        }
    }

    private static boolean canMove(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

    private static class Player {
        private int no;
        private Point point;
        private int dir;
        private int status;
        private int gun;
        private int score;

        public Player(int no, Point point, int dir, int status) {
            this.no = no;
            this.point = point;
            this.dir = dir;
            this.status = status;
            this.score = 0;
        }

        public int row() {
            return point.row;
        }

        public int col() {
            return point.col;
        }

        public int[] dir() {
            return DIRECTIONS[dir];
        }

        public void turnBack() {
            dir = (dir + 2) % 4;
        }

        public void turnRight() {
            dir = (dir + 1) % 4;
        }

        public void moveTo(Point point) {
            this.point = point;
        }

        public void gainGun(int gun) {
            this.gun = gun;
        }

        public void loseGun() {
            this.point.guns.offer(this.gun);
            this.gun = 0;
        }

        public int power() {
            return status + gun;
        }
    }

    private static class Point {
        private int row;
        private int col;
        private PriorityQueue<Integer> guns;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
            this.guns = new PriorityQueue<>(Comparator.reverseOrder());
        }
    }

}
