package problem_1946;

import java.io.*;
import java.util.*;

/* List에 비해 속도가 느릴 가능성이 높다.
 * List는 처음에 한 번만 정렬을 수행하는 반면,
 * PriorityQueue는 요소를 한 개 poll할 때 마다 조정을 해야 하므로 이 과정에서 시간이 더 소요된다. */
public class Problem_1946_PriorityQueue {
    private static class Applicant implements Comparable<Applicant> {
        private int paper;
        private int interview;

        public Applicant(int paper, int interview) {
            this.paper = paper;
            this.interview = interview;
        }

        @Override
        public int compareTo(Applicant o) {
            if (this.paper == o.paper) {
                return this.interview - o.interview;
            }

            return this.paper - o.paper;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            PriorityQueue<Applicant> applicants = new PriorityQueue<>();

            int N = Integer.parseInt(input.readLine());
            while (N-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                applicants.offer(new Applicant(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
            }

            answer.append(select(applicants)).append('\n');
        }

        System.out.print(answer);
    }

    private static int select(PriorityQueue<Applicant> applicants) {
        int num = 1;
        Applicant cur = applicants.poll();

        while (!applicants.isEmpty()) {
            Applicant nxt = applicants.poll();

            if (cur.interview < nxt.interview) {
                continue;
            }

            num++;
            cur = nxt;
        }

        return num;
    }
}
