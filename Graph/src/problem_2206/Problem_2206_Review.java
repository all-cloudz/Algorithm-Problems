package problem_2206;

import java.io.*;
import java.util.*;

// HashMap, HashSet을 사용하면 메모리 초과가 발생한다. boolean[][][]와 같은 논리지만 아무래도 이 문제의 메모리 제한이 빡빡하기 때문이다.
// 하지만 HashMap, HashSet을 사용하는 것이 더 범용적이므로 이러한 방법으로 구현하는 것도 알아둘 필요는 있을 것 같다.
public class Problem_2206_Review {
    private static class Point {
        private int row;
        private int col;
        private int dist;
        private boolean destroyed;

        public Point(int row, int col) {
            this(row, col, 0, false);
        }

        public Point(int row, int col, int dist, boolean destroyed) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.destroyed = destroyed;
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + row;
            hash = hash * 17 + col;
            hash = hash * 17 + dist;
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || !(o instanceof Point)) {
                return false;
            }

            Point that = (Point) o;
            return this.row == that.row && this.col == that.col;
        }
    }

    private static final int[][] DIRECTIONS = new int[][] {
            { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };

    private static int N;
    private static int M;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = input.readLine().toCharArray();
        }

        System.out.println(findShortestDistance());
    }

    private static int findShortestDistance() {
        Queue<Point> points = new ArrayDeque<>();
        Map<Integer, Set<Point>> discovered = new HashMap<>(); // key : destroyLayer, value : discovered
        discovered.put(0, new HashSet<>());
        discovered.put(1, new HashSet<>());

        Point start = new Point(0, 0, 1, false);
        points.offer(start);
        discovered.get(0).add(start);

        while (!points.isEmpty()) {
            Point cur = points.poll();

            if (cur.row == N - 1 && cur.col == M - 1) {
                return cur.dist;
            }

            for (int[] direction : DIRECTIONS) {
                int nextRow = cur.row + direction[0];
                int nextCol = cur.col + direction[1];

                if (!isMovable(nextRow, nextCol)) {
                    continue;
                }

                boolean isWall = (map[nextRow][nextCol] == '1');
                int destroyLayer = (cur.destroyed) ? 1 : 0;
                Point next = new Point(nextRow, nextCol, cur.dist + 1, cur.destroyed);

                if (!isWall && !discovered.get(destroyLayer).contains(next)) {
                    points.offer(next);
                    discovered.get(destroyLayer).add(next);
                    continue;
                }

                if (isWall && !cur.destroyed) {
                    next.destroyed = true;
                    points.offer(next);
                    discovered.get(1).add(next);
                }
            }
        }

        return -1;
    }

    private static boolean isMovable(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M;
    }
}
