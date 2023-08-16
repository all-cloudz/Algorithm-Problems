package backtracking.boj_6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_6987 {

    private static final int TEST_CASE = 4;
    private static final int NATION_COUNT = 6;
    private static final int RESULT_CATEGORY = 3;
    private static final int MAX_GAME_COUNT = 15;

    private static int[][] nations;

    public static void main(final String[] args) throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder answer = new StringBuilder();

        for (int tc = 1; tc <= TEST_CASE; tc++) {
            init(input);

            if (isNotValid(nations)) {
                answer.append("0 ");
                continue;
            }

            answer.append(isPossible(0, 0, 1) ? 1 : 0).append(' ');
        }

        System.out.println(answer);
    }

    private static void init(final BufferedReader input) throws IOException {
        nations = new int[NATION_COUNT][RESULT_CATEGORY];

        final StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < NATION_COUNT; i++) {
            for (int j = 0; j < RESULT_CATEGORY; j++) {
                nations[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static boolean isNotValid(final int[][] nations) {
        return Arrays.stream(nations)
                     .flatMapToInt(Arrays::stream)
                     .sum() != 30;
    }

    private static boolean isPossible(final int gameCount, final int attacker, final int defender) {
        if (gameCount == MAX_GAME_COUNT) {
            return true;
        }

        if (defender == NATION_COUNT) {
            return isPossible(gameCount, attacker + 1, attacker + 2);
        }

        boolean result = false;

        if (nations[attacker][0] > 0 && nations[defender][2] > 0) {
            nations[attacker][0]--;
            nations[defender][2]--;
            result = isPossible(gameCount + 1, attacker, defender + 1);
            nations[attacker][0]++;
            nations[defender][2]++;
        }

        if (!result && nations[attacker][1] > 0 && nations[defender][1] > 0) {
            nations[attacker][1]--;
            nations[defender][1]--;
            result = isPossible(gameCount + 1, attacker, defender + 1);
            nations[attacker][1]++;
            nations[defender][1]++;
        }

        if (!result && nations[attacker][2] > 0 && nations[defender][0] > 0) {
            nations[attacker][2]--;
            nations[defender][0]--;
            result = isPossible(gameCount + 1, attacker, defender + 1);
            nations[attacker][2]++;
            nations[defender][0]++;
        }

        return result;
    }
}
