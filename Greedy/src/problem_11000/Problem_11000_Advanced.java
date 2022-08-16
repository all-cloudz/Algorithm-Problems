
package problem_11000;

import java.io.*;
import java.util.*;

// 한 강의실에서 진행될 수 있는 강의를 리스트에 담는 것까지 할 수 있다.
public class Problem_11000_Advanced {
    private static class Lecture implements Comparable<Lecture> {
        private int start;
        private int end;
        private int classroom;

        public Lecture(int start, int end, int classroom) {
            this.start = start;
            this.end = end;
            this.classroom = classroom;
        }

        public Lecture setClassroom(int classroom) {
            this.classroom = classroom;
            return this;
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
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1);
        }

        Arrays.sort(lectures);

        Map<Integer, List<Lecture>> classrooms = new HashMap<>();
        PriorityQueue<Lecture> endTimes = new PriorityQueue<>((a, b) -> a.end - b.end);

        classrooms.putIfAbsent(lectures[0].classroom, new ArrayList<>());
        classrooms.get(lectures[0].classroom).add(lectures[0]);
        endTimes.offer(lectures[0]);

        for (int size = lectures.length, i = 1; i < size; i++) {
            Lecture startLecture = lectures[i];

            // 기존의 강의실에 강의를 배정하는 경우
            if (endTimes.peek().end <= startLecture.start) {
                Lecture endLecture = endTimes.poll();

                classrooms.get(endLecture.classroom).add(startLecture.setClassroom(endLecture.classroom));
                endTimes.offer(lectures[i]);

                continue;
            }

            // 새로운 강의실에 강의를 배정하는 경우
            int newClassroom = classrooms.size();
            classrooms.putIfAbsent(newClassroom, new ArrayList<>());
            classrooms.get(newClassroom).add(startLecture);
            endTimes.offer(startLecture);
        }

        System.out.println(endTimes.size());
    }
}
