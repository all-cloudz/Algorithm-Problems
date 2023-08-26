package implementation.boj_17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class Boj_17281_Sol1 {

    private static final int PLAYER_COUNT = 9;

    private static int N;
    private static int[][] players;

    public static void main(final String[] args) throws IOException {
        init();
        final List<int[]> orders = generateOrder();
        orders.stream()
              .mapToInt(Boj_17281_Sol1::calculateScore)
              .max()
              .ifPresent(System.out::print);
    }

    private static void init() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        players = new int[N][PLAYER_COUNT];
        for (int i = 0; i < N; i++) {
            players[i] = Arrays.stream(input.readLine().split(" "))
                               .mapToInt(Integer::parseInt)
                               .toArray();
        }
    }

    private static List<int[]> generateOrder() {
        final ArrayList<int[]> orders = new ArrayList<>();
        generateOrder(new int[PLAYER_COUNT], 0, 0, orders);
        return orders;
    }

    private static void generateOrder(final int[] order, final int idx, final int selected, final List<int[]> orders) {
        if (idx == PLAYER_COUNT) {
            orders.add(Arrays.copyOf(order, PLAYER_COUNT));
            return;
        }

        if (idx == 3) {
            generateOrder(order, idx + 1, selected | 1, orders);
            return;
        }

        for (int i = 1; i < PLAYER_COUNT; i++) {
            if ((selected >> i & 1) == 1) {
                continue;
            }

            order[idx] = i;
            generateOrder(order, idx + 1, selected | (1 << i), orders);
        }
    }

    private static int calculateScore(final int[] order) {
        int totalScore = 0;
        int startIdx = 0;

        for (int inning = 0; inning < N; inning++) {
            final GameInfo gameInfo = playInnings(GameInfo.newGame(inning, startIdx, order));
            totalScore += gameInfo.score;
            startIdx = gameInfo.batterInfo.batterIdx;
        }

        return totalScore;
    }

    private static GameInfo playInnings(final GameInfo gameInfo) {
        if (gameInfo.isEnd()) {
            return gameInfo;
        }

        switch (players[gameInfo.innings][gameInfo.getBatter()]) {
            case 0:
                gameInfo.recordOut();
                return playInnings(gameInfo);
            case 1:
                gameInfo.recordSingle();
                return playInnings(gameInfo);
            case 2:
                gameInfo.recordDouble();
                return playInnings(gameInfo);
            case 3:
                gameInfo.recordTriple();
                return playInnings(gameInfo);
            case 4:
                gameInfo.recordHomeRun();
                return playInnings(gameInfo);
            default:
                throw new RuntimeException("이 코드는 실행될 수 없습니다.");
        }
    }

    private static class GameInfo {
        private final int innings;
        private final BatterInfo batterInfo;
        private int base;
        private int outCount;
        private int score;

        public GameInfo(final int innings, final int startBatterIdx, final int[] order) {
            this(innings, startBatterIdx, order, 0, 0, 0);
        }

        public GameInfo(final int innings, final int startBatterIdx, final int[] order, final int base, final int outCount, final int score) {
            this.innings = innings;
            this.batterInfo = new BatterInfo(startBatterIdx, order);
            this.base = base;
            this.outCount = outCount;
            this.score = score;
        }

        public static GameInfo newGame(final int innings, final int startHitterIdx, final int[] order) {
            return new GameInfo(innings, startHitterIdx, order);
        }

        public int getBatter() {
            return batterInfo.getBatter();
        }

        public void recordOut() {
            outCount++;
            batterInfo.nextBatter();
        }

        public boolean isEnd() {
            return outCount == 3;
        }

        public void recordSingle() {
            run(positionGreaterThanOrEqual(2), 1);
            batterInfo.nextBatter();
        }

        public void recordDouble() {
            run(positionGreaterThanOrEqual(1), 2);
            batterInfo.nextBatter();
        }

        public void recordTriple() {
            runAll(3);
            batterInfo.nextBatter();
        }

        public void recordHomeRun() {
            runAll(4);
            batterInfo.nextBatter();
        }

        private void run(final IntPredicate predicate, final int step) {
            for (int i = 2; i >= 0; i--) {
                if ((base >> i & 1) == 0) {
                    continue;
                }

                if (predicate.test(i)) {
                    score++;
                } else {
                    base = base | 1 << (i + step);
                }

                base = base ^ 1 << i;
            }

            if (step == 4) {
                score++;
                return;
            }

            base = base | 1 << (step - 1);
        }

        private void runAll(final int step) {
            run(value -> true, step);
        }

        private static IntPredicate positionGreaterThanOrEqual(final int limit) {
            return position -> position >= limit;
        }
    }

    private static class BatterInfo {
        private int batterIdx;
        private final int[] order;

        public BatterInfo(final int startBatterIdx, final int[] order) {
            this.batterIdx = startBatterIdx;
            this.order = order;
        }

        public int getBatter() {
            return order[batterIdx];
        }

        public void nextBatter() {
            batterIdx = (batterIdx + 1) % 9;
        }
    }
}
