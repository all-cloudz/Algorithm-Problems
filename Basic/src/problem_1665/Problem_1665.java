package problem_1665;

import java.io.*;
import java.util.*;

public class Problem_1665 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(input.readLine());
        while (N-- > 0) {
            int num = Integer.parseInt(input.readLine());

            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            try {
                if (maxHeap.peek() > minHeap.peek()) {
                    swap(maxHeap, minHeap);
                }
            } catch (NullPointerException e) {
            }

            answer.append(maxHeap.peek()).append('\n');
        }

        System.out.println(answer);
    }

    private static void swap(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        maxHeap.offer(minHeap.poll());
        minHeap.offer(maxHeap.poll());
    }
}
