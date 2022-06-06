package problem_1931;

import java.io.*;
import java.util.*;

public class Problem_1931 {
    private static class Meeting implements Comparable<Meeting> {
        private int startTime;
        private int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        /* 종료 시간이 같다면 시작 시간에 대하여 오름차순으로 정렬해야 하는 이유는 다음과 같다.
         * 3
         * 1 4
         * 5 5
         * 4 5
         * 와 같이 입력이 주어질 경우 (1, 4), (5, 5)만 선택하게 된다.
         * 하지만 실제로 정답은 (1, 4), (4, 5), (5, 5)이다.
         * 여기서 이런 문제가 발생한 이유는 시작과 동시에 끝나는 회의가 있기 때문이다.
         * 따라서 이런 예외를 처리하기 위해 종료 시간이 같다면 시작 시간이 작은 것을 앞에 오도록 정렬해야 한다. */
        @Override
        public int compareTo(Meeting o) {
            if (this.endTime != o.endTime) {
                return this.endTime - o.endTime;
            }

            return this.startTime - o.startTime;
        }
    }

    private static Stack<Meeting> answer = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            meetings[i] = new Meeting(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        Arrays.sort(meetings);
        findMaxMeeting(meetings);

        System.out.print(answer.size());
    }

    private static void findMaxMeeting(Meeting[] meetings) {
        answer.push(meetings[0]);

        for (int i = 1; i < meetings.length; i++) {
            Meeting cur = answer.peek();

            if (cur.endTime > meetings[i].startTime) {
                continue;
            }

            answer.push(meetings[i]);
        }
    }
}
