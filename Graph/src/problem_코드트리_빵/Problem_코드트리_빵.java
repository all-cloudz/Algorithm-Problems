package problem_코드트리_빵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_코드트리_빵 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

    private static int n;
    private static int m;
    private static Set<Point> camps;
    private static Store[] stores;
    private static boolean[][] blocked;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        camps = new HashSet<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(tokenizer.nextToken()) == 1) {
                    camps.add(new Point(i, j));
                }
            }
        }

        stores = new Store[m + 1];
        for (int i = 1; i <= m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            stores[i] = new Store(new Point(row, col), false);
        }

        blocked = new boolean[n][n];
        System.out.println(spendTime());
    }

    private static int spendTime() {
        Queue<Person> personQueue = new ArrayDeque<>();
        boolean[][][] discovered = new boolean[m + 1][n][n];
        int time = 0;

        while (!isAllArrived()) {
            blockCheck(personQueue);

            if (!personQueue.isEmpty()) {
                move(personQueue, discovered);
            }

            if (++time <= m) {
                goBasecamp(time, personQueue);
            }
        }

        return time;
    }

    private static void blockCheck(Queue<Person> personQueue) {
        for (Person cur : personQueue) {
            Store store = stores[cur.no];

            if (store.arrived) {
                continue;
            }

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row() + dir[0];
                int nextCol = cur.col() + dir[1];

                if (!canMove(nextRow, nextCol)) {
                    continue;
                }

                if (store.row() == nextRow && store.col() == nextCol) {
                    blocked[nextRow][nextCol] = true;
                    store.arrived = true;
                    break;
                }
            }
        }
    }

    private static void move(Queue<Person> personQueue, boolean[][][] discovered) {
        int size = personQueue.size();
        while (size-- > 0) {
            Person person = personQueue.poll();
            Store store = stores[person.no];

            if (store.arrived) {
                continue;
            }

            for (int[] dir : DIRECTIONS) {
                int nextRow = person.row() + dir[0];
                int nextCol = person.col() + dir[1];

                if (!canMove(nextRow, nextCol) || discovered[person.no][nextRow][nextCol] || blocked[nextRow][nextCol]) {
                    continue;
                }

                personQueue.add(new Person(new Point(nextRow, nextCol), person.no));
                discovered[person.no][nextRow][nextCol] = true;
            }
        }
    }

    private static void goBasecamp(int no, Queue<Person> personQueue) {
        Store target = stores[no];
        Point basecamp = getCloseCamp(target);
        personQueue.add(new Person(basecamp, no));
        blocked[basecamp.row][basecamp.col] = true;
    }

    private static Point getCloseCamp(Store target) {
        Queue<Point> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[n][n];

        points.add(target.point);
        discovered[target.row()][target.col()] = true;

        while (!points.isEmpty()) {
            Point cur = points.poll();

            if (camps.contains(cur)) {
                return cur;
            }

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!canMove(nextRow, nextCol) || discovered[nextRow][nextCol] || blocked[nextRow][nextCol]) {
                    continue;
                }

                points.add(new Point(nextRow, nextCol));
                discovered[nextRow][nextCol] = true;
            }
        }

        throw new RuntimeException("이 코드는 수행될 수 없습니다.");
    }

    private static boolean isAllArrived() {
        for (int i = 1; i <= m; i++) {
            if (!stores[i].arrived) {
                return false;
            }
        }

        return true;
    }

    private static boolean canMove(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }

    private static class Store {

        private Point point;
        private boolean arrived;

        public Store(Point point, boolean arrived) {
            this.point = point;
            this.arrived = arrived;
        }

        public int row() {
            return this.point.row;
        }

        public int col() {
            return this.point.col;
        }

    }

    private static class Person {

        private int no;
        private Point point;

        public Person(Point point, int no) {
            this.point = point;
            this.no = no;
        }

        public int row() {
            return this.point.row;
        }

        public int col() {
            return this.point.col;
        }

    }

    private static class Point {

        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Point)) {
                return false;
            }

            Point that = (Point) o;
            return this.row == that.row && this.col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }

    }

}
