package implementation.problem_2669;

import java.io.*;
import java.util.*;

public class Problem_2669_Sol1 {
    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Rectangle {
        private Point lowLeft;
        private Point highRight;

        public Rectangle(Point lowLeft, Point highRight) {
            this.lowLeft = lowLeft;
            this.highRight = highRight;
        }
    }

    private static Rectangle[] rectangles = new Rectangle[4];

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            rectangles[i] = new Rectangle(new Point(x1, y1), new Point(x2, y2));
        }

        System.out.print(area());
    }

    private static int area() {
        int area = 0;

        int[][] range = range();
        for (int i = range[0][0]; i < range[0][1]; i++) {
            for (int j = range[1][0]; j < range[1][1]; j++) {
                Rectangle cur = new Rectangle(new Point(i, j), new Point(i + 1, j + 1));

                if (contains(cur)) {
                    area++;
                }
            }
        }

        return area;
    }

    private static int[][] range() {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            minX = Math.min(minX, rectangles[i].lowLeft.x);
            maxX = Math.max(maxX, rectangles[i].highRight.x);
            minY = Math.min(minY, rectangles[i].lowLeft.y);
            maxY = Math.max(maxY, rectangles[i].highRight.y);
        }

        return new int[][] {
                new int[] {minX, maxX},
                new int[] {minY, maxY}
        };
    }

    private static boolean contains(Rectangle target) {
        for (Rectangle cur : rectangles) {
            if (cur.lowLeft.x <= target.lowLeft.x && cur.lowLeft.y <= target.lowLeft.y && cur.highRight.x >= target.highRight.x && cur.highRight.y >= target.highRight.y) {
                return true;
            }
        }

        return false;
    }
}
