package problem_1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1107 {

    private static final int DEFAULT_CHANNEL = 100;

    private static int N;
    private static boolean[] usable;
    private static int minPressCount;

    static {
        usable = new boolean[10];
        Arrays.fill(usable, true);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        minPressCount = Math.abs(N - DEFAULT_CHANNEL);
        int M = Integer.parseInt(input.readLine());

        if (M != 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            checkBroken(tokenizer);
        }

        for (int i = 0; i <= 1_000_000; i++) {
            int pressCount = canPress(i);

            if (pressCount > 0) {
                minPressCount = Math.min(minPressCount, pressCount + Math.abs(N - i));
            }
        }

        System.out.println(minPressCount);
    }

    private static int canPress(int i) {
        int pressCount = 0;

        if (i == 0 && usable[i]) {
            pressCount++;
        }

        while (i > 0) {
            if (!usable[i % 10]) {
                return 0;
            }

            pressCount++;
            i /= 10;
        }

        return pressCount;
    }

    private static void checkBroken(StringTokenizer tokenizer) {
        while (tokenizer.hasMoreTokens()) {
            int brokenButton = Integer.parseInt(tokenizer.nextToken());
            usable[brokenButton] = false;
        }
    }

}
