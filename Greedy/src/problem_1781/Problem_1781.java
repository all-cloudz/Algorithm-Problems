package problem_1781;

import java.io.*;
import java.util.*;

public class Problem_1781 {
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

    private static PriorityQueue<Problem> problems = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int deadline = Integer.parseInt(tokenizer.nextToken());
            int ramen = Integer.parseInt(tokenizer.nextToken());

            problems.offer(new Problem(deadline, ramen));
        }

        System.out.print(solve(N));
    }

    /* 처음 설정해둔 그리디 기준은 deadline이 작은 것을 우선적으로 풀고, 그 값이 같다면 ramen이 큰 것을 우선적으로 푸는 것이다. 하지만,
     * 3
     * 1 100
     * 2 300
     * 2 400
     * 의 경우 (1, 100), (2, 400)보다 (2, 400), (2, 300)을 푸는 것이 더 많은 컵라면을 받을 수 있음에도
     * Problem 클래스의 메서드 compareTo에 따라 하나씩 선택할 경우 (1, 100), (2, 300)을 선택하게 된다.
     * 이처럼 처음 설정해둔 그리디 기준에 맞는 문제를 선별한 후에 '선별한 것을 교체'해야 한다면 새로운 그리디 기준을 설정해야 한다.
     * 이 문제에서는 새로운 그리디 기준을 푼 문제 중에서 받는 라면의 수가 작은 문제는 안 푼 문제 중에서 받는 라면의 수가 큰 문제으로 대체하는 것이므로 우선순위 큐를 사용할 수 있다. */

    private static int solve(int maxTime) {
        int time = 0;
        PriorityQueue<Problem> solved = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                return o1.ramen - o2.ramen;
            }
        });

        while (!problems.isEmpty()) {
            if (time < problems.peek().deadline) {
                solved.offer(problems.poll());
                time++;
                continue;
            }

            if (problems.peek().ramen > solved.peek().ramen) {
                solved.poll();
                solved.offer(problems.poll());
                continue;
            }

            problems.poll();
        }

        int sum = 0;
        for (Problem problem : solved) {
            sum += problem.ramen;
        }

        return sum;
    }
}
