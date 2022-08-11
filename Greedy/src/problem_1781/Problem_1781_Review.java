package problem_1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 제한 조건 + 최대 개수 = 그리디
public class Problem_1781_Review {
    private static class Problem implements Comparable<Problem> {
        private int deadline;
        private int ramen;

        public Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.deadline == o.deadline) {
                return o.ramen - this.ramen;
            }

            return this.deadline - o.deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Problem> deadlineHeap = new PriorityQueue<>();
        PriorityQueue<Problem> solved = new PriorityQueue<>((a, b) -> a.ramen - b.ramen);
        int deadline = 0;

        final int N = Integer.parseInt(input.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            deadlineHeap.offer(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while (!deadlineHeap.isEmpty()) {
            Problem cur = deadlineHeap.poll();

            if (cur.deadline > deadline) {
                solved.offer(cur);
                deadline++;
                continue;
            }

            if (!solved.isEmpty() && solved.peek().ramen < cur.ramen) {
                deadlineHeap.offer(solved.poll());
                solved.offer(cur);
            }
        }

        System.out.println(solved.stream().mapToInt(s -> s.ramen).sum());
    }
}
