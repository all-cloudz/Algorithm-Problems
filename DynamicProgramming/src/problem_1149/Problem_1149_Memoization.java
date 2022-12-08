package problem_1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_1149_Memoization {

    private static class Cost {
        private int redCost;
        private int greenCost;
        private int blueCost;

        public Cost() {
        }

        public Cost(int redCost, int greenCost, int blueCost) {
            this.redCost = redCost;
            this.greenCost = greenCost;
            this.blueCost = blueCost;
        }

        public int getMinCost() {
            int minCost = Math.min(redCost, greenCost);
            return Math.min(minCost, blueCost);
        }

        public int getMinCostExceptColor(String color) {
            if ("red".equals(color)) {
                return Math.min(greenCost, blueCost);
            }

            if ("green".equals(color)) {
                return Math.min(redCost, blueCost);
            }

            if ("blue".equals(color)) {
                return Math.min(redCost, greenCost);
            }

            throw new IllegalArgumentException();
        }
    }

    private static int N;
    private static Cost[] costs;
    private static Cost[] caches;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        costs = new Cost[N];
        caches = new Cost[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int redCost = Integer.parseInt(tokenizer.nextToken());
            int greenCost = Integer.parseInt(tokenizer.nextToken());
            int blueCost = Integer.parseInt(tokenizer.nextToken());
            costs[i] = new Cost(redCost, greenCost, blueCost);
        }

        caches[0] = costs[0];
        memoize(N - 1);
        System.out.println(caches[N - 1].getMinCost());
    }

    private static Cost memoize(int current) {
        if (caches[current] == null) {
            Cost prefixCost = memoize(current - 1);

            caches[current] = new Cost();
            caches[current].redCost = prefixCost.getMinCostExceptColor("red") + costs[current].redCost;
            caches[current].greenCost = prefixCost.getMinCostExceptColor("green") + costs[current].greenCost;
            caches[current].blueCost = prefixCost.getMinCostExceptColor("blue") + costs[current].blueCost;
        }

        return caches[current];
    }

}
