package code_외판원_순회_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Code_외판원_순회_3 {

    private static int n;
    private static int k;
    private static int[][] costs;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        init();
        fillTable();
        System.out.println(minCost());
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            costs[i] = Arrays.stream(input.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        cache = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                cache[i][j] = (int) 1e9;
            }
        }
    }

    private static void fillTable() {
        cache[1 << 0][0] = 0;

        for (int visited = 0; visited < (1 << n); visited++) {
            for (int cur = 0; cur < n; cur++) {
                if ((visited >> cur & 1) == 0) {
                    continue;
                }

                fillTable(visited, cur);
            }
        }
    }

    private static void fillTable(int visited, int cur) {
        for (int next = 0; next < n; next++) {
            if ((visited >> next & 1) == 1) {
                continue;
            }

            if (costs[cur][next] == 0) {
                continue;
            }

            cache[visited | (1 << next)][next] = Math.min(
                    cache[visited | (1 << next)][next], cache[visited][cur] + costs[cur][next]
            );
        }
    }

    private static int minCost() {
        int minCost = Integer.MAX_VALUE;

        for (int visited = 0; visited < (1 << n); visited++) {
            if (countOneBit(visited) != k) {
                continue;
            }

            for (int cur = 1; cur < n; cur++) {
                if ((visited >> cur & 1) == 0) {
                    continue;
                }

                if (costs[cur][0] == 0) {
                    continue;
                }

                minCost = Math.min(minCost, cache[visited][cur] + costs[cur][0]);
            }
        }

        return minCost;
    }

    private static int countOneBit(int number) {
        int count = 0;

        for (int i = 1; i < n; i++) {
            if ((number >> i & 1) == 1) {
                count++;
            }
        }

        return count;
    }

}
