package problem_1946;

import java.io.*;
import java.util.*;

public class Problem_1946_List {
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
            List<Applicant> applicants = new ArrayList<>();

            int N = Integer.parseInt(input.readLine());
            while (N-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                applicants.add(new Applicant(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
            }
            Collections.sort(applicants);

            answer.append(select(applicants)).append('\n');
        }

        System.out.print(answer);
    }

    private static int select(List<Applicant> applicants) {
        List<Applicant> selected = new ArrayList<>();
        int size = 0;

        selected.add(applicants.get(0));
        size++;

        for (int i = 1; i < applicants.size(); i++) {
            if (selected.get(size - 1).interview < applicants.get(i).interview) {
                continue;
            }

            selected.add(applicants.get(i));
            size++;
        }

        return size;
    }
}

