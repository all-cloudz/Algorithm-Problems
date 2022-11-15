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

        channels:
        for (int i = 0; i <= 1_000_000; i++) {
            String converted = String.valueOf(i);

            for (char cur : converted.toCharArray()) {
                if (!usable[cur - '0']) {
                    continue channels;
                }
            }

            minPressCount = Math.min(minPressCount, converted.length() + Math.abs(N - i));
        }

        System.out.println(minPressCount);
    }

    private static void checkBroken(StringTokenizer tokenizer) {
        while (tokenizer.hasMoreTokens()) {
            int brokenButton = Integer.parseInt(tokenizer.nextToken());
            usable[brokenButton] = false;
        }
    }

}
