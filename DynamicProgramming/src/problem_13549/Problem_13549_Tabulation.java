package problem_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_13549_Tabulation {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] tabulated = new int[K + 1];
        fillTable(tabulated, N, K);
        System.out.println(tabulated[K]);
    }

    private static void fillTable(int[] tabulated, int departure, int arrival) {
        int size = Math.min(departure, arrival);
        for (int i = 0; i <= size; i++) {
            tabulated[i] = departure - i;
        }

        for (int i = departure + 1; i <= arrival; i++) {
            if (i % 2 == 0) {
                tabulated[i] = Math.min(tabulated[i >> 1], tabulated[i - 1] + 1);
                continue;
            }

            tabulated[i] = Math.min(tabulated[(i - 1) >> 1], tabulated[(i + 1) >> 1]) + 1;
            tabulated[i] = Math.min(tabulated[i], tabulated[i - 1] + 1);
        }
    }

}
