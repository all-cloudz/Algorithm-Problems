package problem_1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1010_Tabulation {
    private static final int[][] tabulated = new int[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (i == j || j == 0) {
                    tabulated[i][j] = 1;
                    continue;
                }

                if (i < j) {
                    continue;
                }

                tabulated[i][j] = tabulated[i - 1][j - 1] + tabulated[i - 1][j];
            }
        }

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            answer.append(tabulated[M][N]).append('\n');
        }

        System.out.println(answer);
    }
}
