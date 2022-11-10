package problem_1231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1231 {

    private static int C, D;
    private static int M;
    private static int[][] stocks;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        C = Integer.parseInt(tokenizer.nextToken());
        D = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        stocks = new int[C][D];
        for (int i = 0; i < C; i++) {
            stocks[i] = Arrays.stream(input.readLine().split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();
        }

        for (int i = 1; i < D; i++) {
            M += getMaxOfInvestmentProfit(i);
        }

        System.out.println(M);
    }

    private static int getMaxOfInvestmentProfit(int tomorrow) {
        int today = tomorrow - 1;
        int[] cache = new int[M + 1];

        for (int i = 0; i < C; i++) {
            int curStockPrice = stocks[i][today];
            int deltaStockPrice = stocks[i][tomorrow] - curStockPrice;

            for (int j = curStockPrice; j <= M; j++) {
                cache[j] = Math.max(cache[j], deltaStockPrice + cache[j - curStockPrice]);
            }
        }

        return cache[M];
    }

}
