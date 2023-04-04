package problem_코드트리_빵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem_코드트리_빵 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

    private static int n;
    private static int m;
    private static Set<Point> houses;
    private static Point[] stores;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        houses = new HashSet<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(tokenizer.nextToken()) == 1) {
                    houses.add(new Point(i, j));
                }
            }
        }

        stores = new Point[m + 1];
        for (int i = 1; i <= m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            stores[i] = new Point(row, col);
        }

        System.out.println(spendTime());
    }

    private static int spendTime() {
        Queue<Person> people = new ArrayDeque<>();
        boolean[] arrived = new boolean[m + 1];
        boolean[][][] discovered = new boolean[m + 1][n][n];
        boolean[][] blocked = new boolean[n][n];

        int time = 1;
        init(people, discovered, blocked, time);

        while (!people.isEmpty()) {
            int size = people.size();
            List<Point> blocks = simulate(size, people, arrived, discovered, blocked);
            blockAll(blocked, blocks);

            if (isAllArrived(arrived)) {
                return time;
            }

            if (++time <= m) {
                init(people, discovered, blocked, time);
            }
        }

        throw new IllegalAccessError("이 코드는 실행될 수 없습니다");
    }

    private static void init(Queue<Person> people, boolean[][][] discovered, boolean[][] blocked, int time) {
        Point house = getBasecamp(blocked, stores[time]);
        people.add(new Person(time, house));
        discovered[time][house.row][house.col] = true;
        blocked[house.row][house.col] = true;
    }

    private static List<Point> simulate(int size, Queue<Person> people, boolean[] arrived, boolean[][][] discovered, boolean[][] blocked) {
        List<Point> blocks = new ArrayList<>();

        while (size-- > 0) {
            Person cur = people.poll();

            if (arrived[cur.no]) {
                continue;
            }

            if (cur.point.equals(stores[cur.no])) {
                arrived[cur.no] = true;
                blocks.add(cur.point);
                continue;
            }

            searchBreadth(cur, people, discovered, blocked);
        }

        return blocks;
    }

    private static void searchBreadth(Person cur, Queue<Person> people, boolean[][][] discovered, boolean[][] blocked) {
        for (int[] dir : DIRECTIONS) {
            int nextRow = cur.point.row + dir[0];
            int nextCol = cur.point.col + dir[1];

            if (!canMove(nextRow, nextCol) || blocked[nextRow][nextCol]) {
                continue;
            }

            if (!discovered[cur.no][nextRow][nextCol]) {
                people.add(new Person(cur.no, new Point(nextRow, nextCol)));
                discovered[cur.no][nextRow][nextCol] = true;
            }
        }
    }

    private static boolean isAllArrived(boolean[] arrived) {
        for (int i = 1; i <= m; i++) {
            if (!arrived[i]) {
                return false;
            }
        }

        return true;
    }

    private static void blockAll(boolean[][] blocked, List<Point> points) {
        for (int i = 1; i <= m; i++) {
            for (Point point : points) {
                blocked[point.row][point.col] = true;
            }
        }
    }

    private static Point getBasecamp(boolean[][] blocked, Point store) {
        Queue<Point> points = new ArrayDeque<>();
        boolean[][] discovered = new boolean[n][n];

        points.add(store);
        discovered[store.row][store.col] = true;

        while (!points.isEmpty()) {
            Point cur = points.poll();

            if (houses.contains(cur)) {
                return cur;
            }

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!canMove(nextRow, nextCol) || blocked[nextRow][nextCol]) {
                    continue;
                }

                if (!discovered[nextRow][nextCol]) {
                    discovered[nextRow][nextCol] = true;
                    points.add(new Point(nextRow, nextCol));
                }
            }
        }

        throw new IllegalAccessError("이 코드는 실행될 수 없습니다");
    }

    private static boolean canMove(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
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

    private static class Person {
        private int no;
        private Point point;

        public Person(int no, Point point) {
            this.no = no;
            this.point = point;
        }
    }

}
