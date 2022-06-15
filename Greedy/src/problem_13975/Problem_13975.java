package problem_13975;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_13975 {
    private static StringBuilder answer = new StringBuilder();
    private static PriorityQueue<Long> costs = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 0; i < T; i++) {
            final int K = Integer.parseInt(input.readLine());

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < K; j++) {
                costs.add(Long.parseLong(tokenizer.nextToken()));
            }

            combineCost();
        }

        System.out.print(answer);
    }

    private static void combineCost() {
        long sum = 0;

        while (costs.size() > 1) {
            long cost = costs.poll() + costs.poll();
            costs.add(cost);
            sum += cost;
        }

        answer.append(sum).append('\n');
        costs.clear();
    }
}
