package problem_d4_3316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_D4_3316 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer.append('#').append(tc).append(' ');

            char[] admins = input.readLine().toCharArray();
            int N = admins.length;
            int[][] cache = new int[N + 1][16];

            for (int day = 1; day <= N; day++) {
                int admin = 1 << (admins[day - 1] - 'A');

                for (int i = 1; i < 16; i++) {
                    if (day == 1) {
                        if ((i & 1) != 0 && (i & admin) != 0) {
                            cache[day][i] = 1;
                        }
                    }

                    if (cache[day - 1][i] == 0) {
                        continue;
                    }

                    for (int j = 1; j < 16; j++) {
                        if ((i & j) != 0 && (j & admin) != 0) {
                            cache[day][j] += cache[day - 1][i];
                            cache[day][j] %= 1_000_000_007;
                        }
                    }
                }
            }

            int count = 0;
            for (int i = 1; i < 16; i++) {
                count += cache[N][i];
                count %= 1_000_000_007;
            }
            answer.append(count).append('\n');
        }

        System.out.println(answer);

    }

}
