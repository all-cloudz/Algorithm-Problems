package problem_1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Problem_1715_Review {

    private static PriorityQueue<Integer> minHeap;

    static {
        minHeap = new PriorityQueue<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            int card = Integer.parseInt(input.readLine());
            minHeap.offer(card);
        }

        while (minHeap.size() > 1) {
            int cardA = minHeap.poll();
            int cardB = minHeap.poll();

            answer += cardA + cardB;
            minHeap.offer(cardA + cardB);
        }

        System.out.println(answer);
    }
}
