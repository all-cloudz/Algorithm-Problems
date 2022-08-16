package problem_11000;

import java.io.*;
import java.util.*;

public class Problem_11000 {
    private static class Lecture implements Comparable<Lecture> {
        private int start;
        private int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }

            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        Lecture[] lectures = new Lecture[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures);

        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer(lectures[0].end);

        for (int size = lectures.length, i = 1; i < size; i++) {
            if (endTimes.peek() <= lectures[i].start) {
                endTimes.poll();
            }

            endTimes.offer(lectures[i].end);
        }

        System.out.println(endTimes.size());
    }
}
