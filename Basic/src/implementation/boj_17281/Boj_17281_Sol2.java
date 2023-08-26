package implementation.boj_17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.IntPredicate;

public class Boj_17281_Sol2 {

    private static final int PLAYER_COUNT = 9;

    private static int N;
    private static int[][] players;

    public static void main(final String[] args) throws IOException {
        init();
        System.out.println(maxScore(new int[N], 0, 0));
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

    private static int maxScore(final int[] order, final int batterIdx, final int selected) {
        if (batterIdx == PLAYER_COUNT) {
            return calculateScore(order);
        }

        if (batterIdx == 3) {
            return maxScore(order, batterIdx + 1, selected | 1);
        }

        int max = Integer.MIN_VALUE;

        for (int player = 1; player < PLAYER_COUNT; player++) {
            if ((selected >> player & 1) == 1) {
                continue;
            }

            order[batterIdx] = player;
            max = Math.max(max, maxScore(order, batterIdx + 1, selected | (1 << player)));
        }

        return max;
    }

    private static int calculateScore(final int[] order) {
        int totalScore = 0;
        int startBatterIdx = 0;

        for (int inning = 0; inning < N; inning++) {
            final InningInfo inningResult = playInnings(InningInfo.newGame(inning, startBatterIdx, order));
            totalScore += inningResult.score;
            startBatterIdx = inningResult.getBatterIdx();
        }

        return totalScore;
    }

    private static InningInfo playInnings(final InningInfo inningInfo) {
        if (inningInfo.isEnd()) {
            return inningInfo;
        }

        switch (players[inningInfo.inning][inningInfo.getBatter()]) {
            case 0:
                inningInfo.recordOut();
                break;
            case 1:
                inningInfo.recordSingle();
                break;
            case 2:
                inningInfo.recordDouble();
                break;
            case 3:
                inningInfo.recordTriple();
                break;
            case 4:
                inningInfo.recordHomeRun();
                break;
            default:
                throw new RuntimeException("이 코드는 실행될 수 없습니다.");
        }

        return playInnings(inningInfo);
    }

    private static class InningInfo {
        private final int inning;
        private final BatterInfo batterInfo;
        private int base;
        private int outCount;
        private int score;

        public InningInfo(final int inning, final int startBatterIdx, final int[] order) {
            this(inning, startBatterIdx, order, 0, 0, 0);
        }

        public InningInfo(
                final int inning,
                final int startBatterIdx,
                final int[] order,
                final int base,
                final int outCount,
                final int score
        ) {
            this.inning = inning;
            this.batterInfo = new BatterInfo(startBatterIdx, order);
            this.base = base;
            this.outCount = outCount;
            this.score = score;
        }

        public static InningInfo newGame(final int inning, final int startBatterIdx, final int[] order) {
            return new InningInfo(inning, startBatterIdx, order);
        }

        public int getBatter() {
            return batterInfo.getBatter();
        }

        public int getBatterIdx() {
            return batterInfo.batterIdx;
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
            runRunners(predicate, step);
            runBatter(step);
        }

        private void runAll(final int step) {
            run(value -> true, step);
        }

        private void runRunners(final IntPredicate predicate, final int step) {
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
        }

        private void runBatter(final int step) {
            if (step == 4) {
                score++;
                return;
            }

            base = base | 1 << (step - 1);
        }

        private static IntPredicate positionGreaterThanOrEqual(final int standard) {
            return position -> position >= standard;
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
