package implementation.problem_예술성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Problem_예술성 {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    private static int n;
    private static int[][] picture;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        picture = new int[n][n];
        for (int i = 0; i < n; i++) {
            picture[i] = Arrays.stream(input.readLine().split(" "))
                               .mapToInt(Integer::parseInt)
                               .toArray();
        }

        int totalScore = 0;
        for (int i = 0; i < 4; i++) {
            if (i != 0) {
                rotatePicture();
            }

            boolean[][] discovered = new boolean[n][n];
            totalScore += sumScores(discovered);
        }

        System.out.println(totalScore);
    }

    private static void rotatePicture() {
        int[][] rotated = new int[n][n];
        rotateCross(rotated);
        rotateSquares(rotated);
        picture = rotated;
    }

    private static void rotateSquares(int[][] rotated) {
        int idx = n >> 1;
        rotateSquare(rotated, 0, 0);
        rotateSquare(rotated, idx + 1, 0);
        rotateSquare(rotated, 0, idx + 1);
        rotateSquare(rotated, idx + 1, idx + 1);
    }

    private static void rotateSquare(int[][] rotated, int row, int col) {
        int size = n >> 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[row + i][col + j] = picture[row + size - j - 1][col + i];
            }
        }
    }

    private static void rotateCross(int[][] rotated) {
        int idx = n >> 1;
        rotated[idx][idx] = picture[idx][idx];

        for (int i = 0; i < idx; i++) {
            rotated[idx][i] = picture[i][idx];
            rotated[n - i - 1][idx] = picture[idx][i];
            rotated[idx][n - i - 1] = picture[n - i - 1][idx];
            rotated[i][idx] = picture[idx][n - i - 1];
        }
    }

    private static int sumScores(boolean[][] discovered) {
        int score = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (discovered[row][col]) {
                    continue;
                }

                score += calculateScore(discovered, row, col);
            }
        }

        return score;
    }

    private static int calculateScore(boolean[][] discovered, int row, int col) {
        Set<Point> area = getArea(discovered, row, col);
        List<Set<Point>> adjAreas = getAdjAreas(new boolean[n][n], discovered, area);

        int score = 0;
        for (Set<Point> adjArea : adjAreas) {
            int sumSize = area.size() + adjArea.size();
            int edgeCnt = countEdge(area, adjArea);
            score += sumSize * valueOfArea(area) * valueOfArea(adjArea) * edgeCnt;
        }

        return score;
    }

    private static int countEdge(Set<Point> area, Set<Point> adjArea) {
        int cnt = 0;

        for (Point cur : area) {
            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!canMove(nextRow, nextCol)) {
                    continue;
                }

                if (adjArea.contains(new Point(nextRow, nextCol))) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static Set<Point> getArea(boolean[][] discovered, int row, int col) {
        Set<Point> area = new HashSet<>();
        Deque<Point> points = new ArrayDeque<>();

        points.push(new Point(row, col));
        discovered[row][col] = true;

        while (!points.isEmpty()) {
            Point cur = points.pop();
            area.add(cur);

            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!canMove(nextRow, nextCol) || discovered[nextRow][nextCol]) {
                    continue;
                }

                if (picture[cur.row][cur.col] == picture[nextRow][nextCol]) {
                    points.push(new Point(nextRow, nextCol));
                    discovered[nextRow][nextCol] = true;
                }
            }
        }

        return area;
    }

    private static List<Set<Point>> getAdjAreas(boolean[][] localDiscovered, boolean[][] globalDiscovered, Set<Point> area) {
        List<Set<Point>> adjAreas = new ArrayList<>();

        for (Point cur : area) {
            for (int[] dir : DIRECTIONS) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (!canMove(nextRow, nextCol) || localDiscovered[nextRow][nextCol] || globalDiscovered[nextRow][nextCol]) {
                    continue;
                }

                if (picture[cur.row][cur.col] != picture[nextRow][nextCol]) {
                    adjAreas.add(getArea(localDiscovered, nextRow, nextCol));
                }
            }
        }

        return adjAreas;
    }

    private static int valueOfArea(Set<Point> area) {
        Point point = area.stream()
                          .findFirst()
                          .orElseThrow();

        return picture[point.row][point.col];
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
            return this.row == that.row
                    && this.col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

    }

}
