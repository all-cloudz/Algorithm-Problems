package problem_d4_3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_D4_3000 {

    private static final int DIVISOR = 20171109;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.reverseOrder());

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int A = Integer.parseInt(tokenizer.nextToken());

            maxHeap.add(A);
            int printValue = 0;
            while (N-- > 0) {
                tokenizer = new StringTokenizer(input.readLine());
                int X = Integer.parseInt(tokenizer.nextToken());
                int Y = Integer.parseInt(tokenizer.nextToken());

                minHeap.add(X);
                minHeap.add(Y);

                while (maxHeap.size() <= minHeap.size()) {
                    maxHeap.add(minHeap.poll());
                }

                while (maxHeap.peek() < minHeap.peek()) {
                    int maxValue = maxHeap.poll();
                    int minValue = minHeap.poll();

                    maxHeap.add(minValue);
                    minHeap.add(maxValue);
                }

                printValue = (printValue + maxHeap.peek()) % DIVISOR;
            }

            answer.append(String.format("#%d %d%n", tc, printValue));
        }

        System.out.println(answer);
    }

}
