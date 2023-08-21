package implementation.problem_17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Problem_17135_Review {

    private static int N;
    private static int M;
    private static int D;
    private static int[][] map;
    private static Set<Unit> enemies;

    public static void main(final String[] args) throws IOException {
        init();
        final int answer = maxKillCount(new Unit[3], 0, 0);
        System.out.println(answer);
    }

    private static void init() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        D = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(input.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        }

        enemies = new HashSet<>();
        initEnemies();
    }

    private static void initEnemies() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    enemies.add(new Unit(i, j));
                }
            }
        }
    }

    private static int maxKillCount(final Unit[] archers, final int idx, final int start) {
        if (idx == 3) {
            return simulateKillEnemies(archers);
        }

        int max = 0;

        for (int i = start; i < M; i++) {
            archers[idx] = new Unit(N, i);
            max = Math.max(max, maxKillCount(archers, idx + 1, i + 1));
        }

        return max;
    }

    private static int simulateKillEnemies(Unit[] archers) {
        int killCount = 0;

        while (!enemies.isEmpty()) {
            final Set<Unit> aimed = aimEnemies(archers);
            killCount += killEnemies(aimed);
            archers = moveUpArchers(archers);
            exceptEnemies(archers[0].row);
        }

        initEnemies();
        return killCount;
    }

    private static Set<Unit> aimEnemies(final Unit[] archers) {
        final Set<Unit> aimed = new HashSet<>();

        for (final Unit archer : archers) {
            enemies.stream()
                   .filter(enemy -> archer.distance(enemy) <= D)
                   .min(
                           Comparator.comparingInt(archer::distance)
                                     .thenComparingInt(enemy -> enemy.col)
                   )
                   .ifPresent(aimed::add);
        }

        return aimed;
    }

    private static int killEnemies(final Set<Unit> aimed) {
        enemies.removeAll(aimed);
        return aimed.size();
    }

    private static Unit[] moveUpArchers(final Unit[] archers) {
        return Arrays.stream(archers)
                     .map(Unit::moveUp)
                     .toArray(Unit[]::new);
    }

    private static void exceptEnemies(final int exceptLine) {
        enemies = enemies.stream()
                         .filter(unit -> unit.isAbove(exceptLine))
                         .collect(Collectors.toSet());
    }

    private static class Unit {
        private int row;
        private final int col;

        public Unit(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        public int distance(final Unit other) {
            return Math.abs(row - other.row) + Math.abs(col - other.col);
        }

        public Unit moveUp() {
            return new Unit(row - 1, col);
        }

        public boolean isUnder(final int exceptLine) {
            return this.row >= exceptLine;
        }

        public boolean isAbove(final int exceptLine) {
            return !isUnder(exceptLine);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Unit)) {
                return false;
            }

            final Unit that = (Unit) o;
            return row == that.row && col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
