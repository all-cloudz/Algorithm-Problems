package code_회의실_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Code_회의실_준비 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        List<Interval> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            meetings.add(new Interval(start, end));
        }

        meetings.sort(Comparator.comparing(i -> i.end));

        Deque<Interval> maxMeetings = new ArrayDeque<>();
        for (Interval meeting : meetings) {
            if (!maxMeetings.isEmpty() && maxMeetings.peekLast().end > meeting.start) {
                continue;
            }

            maxMeetings.add(meeting);
        }

        System.out.println(maxMeetings.size());
    }

    private static class Interval {
        private int start;
        private int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
