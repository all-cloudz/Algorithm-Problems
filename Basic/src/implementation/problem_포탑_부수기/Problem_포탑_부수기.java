package implementation.problem_포탑_부수기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_포탑_부수기 {

    public static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    public static final int[][] DIAGONALS = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

    private static int N;
    private static int M;
    private static int[][] towers;
    private static int time;
    private static int[][] attackTime;
    private static boolean[][] battled;
    private static int K;

    private static final Comparator<int[]> towerComparator = (a, b) -> {
        if (towers[a[0]][a[1]] != towers[b[0]][b[1]]) {
            return towers[a[0]][a[1]] - towers[b[0]][b[1]];
        }

        if (attackTime[a[0]][a[1]] != attackTime[b[0]][b[1]]) {
            return attackTime[b[0]][b[1]] - attackTime[a[0]][a[1]];
        }

        if (a[0] + a[1] != b[0] + b[1]) {
            return (b[0] + b[1]) - (a[0] + a[1]);
        }

        return b[1] - a[1];
    };

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        towers = new int[N][M];
        for (int i = 0; i < N; i++) {
            towers[i] = Arrays.stream(input.readLine().split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();
        }

        time = 0;
        attackTime = new int[N][M];
        battled = new boolean[N][M];

        while (++time <= K) {
            if (existAliveOnlyOne()) {
                break;
            }

            int[] attacker = findMinPowerArg();
            powerUp(attacker);

            int[] defender = findMaxPowerArgExcept(attacker);
            findRouteAndAttack(attacker, defender);

            repairSomeTowers();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    battled[i][j] = false;

                    if (towers[i][j] < 0) {
                        towers[i][j] = 0;
                    }
                }
            }
        }

        int[] survivor = findMaxPowerArg();
        System.out.println(towers[survivor[0]][survivor[1]]);
    }

    private static void powerUp(int[] attacker) {
        attackTime[attacker[0]][attacker[1]] = time;
        battled[attacker[0]][attacker[1]] = true;
        towers[attacker[0]][attacker[1]] += N + M;
    }

    private static int[] findMinPowerArg() {
        int[] arg = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isAlive(i, j)) {
                    continue;
                }

                int[] cur = new int[] { i, j };
                if (arg == null || towerComparator.compare(arg, cur) > 0) {
                    arg = cur;
                }
            }
        }

        return arg;
    }

    private static int[] findMaxPowerArg() {
        return findMaxPowerArgExcept(null);
    }

    private static int[] findMaxPowerArgExcept(int[] excepted) {
        int[] arg = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (excepted != null && i == excepted[0] && j == excepted[1]) {
                    continue;
                }

                if (!isAlive(i, j)) {
                    continue;
                }

                int[] cur = { i, j };
                if (arg == null || towerComparator.compare(arg, cur) < 0) {
                    arg = cur;
                }
            }
        }

        return arg;
    }

    private static void findRouteAndAttack(int[] from, int[] to) {
        Queue<int[]> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[N][M];
        Map<Integer, int[]> route = new HashMap<>();
        points.offer(from);
        discovered[from[0]][from[1]] = true;

        while (!points.isEmpty()) {
            int[] cur = points.poll();

            if (cur[0] == to[0] && cur[1] == to[1]) {
                attackLaser(from, route, to);
                return;
            }

            for (int[] dir : DIRECTIONS) {
                int nextRow = (cur[0] + dir[0] + N) % N;
                int nextCol = (cur[1] + dir[1] + M) % M;

                if (!isAlive(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                int[] next = { nextRow, nextCol };
                points.offer(next);
                discovered[nextRow][nextCol] = true;
                route.put(Arrays.hashCode(next), cur);
            }
        }

        attackBomb(from, to);
    }

    private static void attackLaser(int[] from, Map<Integer, int[]> route, int[] to) {
        int power = towers[from[0]][from[1]];
        int[] cur = to;

        while (cur[0] != from[0] || cur[1] != from[1]) {
            battled[cur[0]][cur[1]] = true;

            if (cur == to) {
                towers[cur[0]][cur[1]] -= power;
            } else {
                towers[cur[0]][cur[1]] -= (power >> 1);
            }

            cur = route.get(Arrays.hashCode(cur));
        }
    }

    private static void attackBomb(int[] from, int[] to) {
        int power = towers[from[0]][from[1]];

        battled[to[0]][to[1]] = true;
        towers[to[0]][to[1]] -= power;

        splash(from, to, power >> 1, DIRECTIONS);
        splash(from, to, power >> 1, DIAGONALS);
    }

    private static void splash(int[] from, int[] to, int power, int[][] dirs) {
        for (int[] dir : dirs) {
            int nextRow = (to[0] + dir[0] + N) % N;
            int nextCol = (to[1] + dir[1] + M) % M;

            if (nextRow == from[0] && nextCol == from[1]) {
                continue;
            }

            battled[nextRow][nextCol] = true;
            towers[nextRow][nextCol] -= power;
        }
    }

    private static void repairSomeTowers() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isAlive(i, j) && !battled[i][j]) {
                    towers[i][j]++;
                }
            }
        }
    }

    private static boolean isAlive(int row, int col) {
        return towers[row][col] > 0;
    }

    private static boolean existAliveOnlyOne() {
        int liveCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (towers[i][j] > 0) {
                    liveCnt++;
                }
            }
        }

        return liveCnt == 1;
    }

}
