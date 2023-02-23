package problem_d3_3283;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_3282 {

    private static class Item {
        private int weight;
        private int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static int N;
    private static Item[] items;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            items = new Item[N + 1];
            K = Integer.parseInt(tokenizer.nextToken());

            for (int i = 1; i <= N; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                int weight = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                items[i] = new Item(weight, value);
            }

            answer.append(String.format("#%d %d%n", tc, maxTotalValue()));
        }

        System.out.println(answer);
    }

    private static int maxTotalValue() {
        int[][] cache = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                cache[i][j] = cache[i - 1][j];

                if (items[i].weight <= j) {
                    cache[i][j] = Math.max(cache[i][j], cache[i - 1][j - items[i].weight] + items[i].value);
                }
            }
        }

        return cache[N][K];
    }

}
