package implementation.code_잔해물을_덮기_위한_사각형의_최소_넓이;

import java.util.Scanner;

public class Code_잔해물을_덮기_위한_사각형의_최소_넓이_Sol2 {

    private static final int OFFSET = 1_000;

    private static int[][] map;
    private static Rectangle first;

    public static void main(String[] args) {
        init();
        System.out.println(area());
    }

    private static void init() {
        Scanner input = new Scanner(System.in);
        map = new int[2001][2001];

        first = new Rectangle(
                1,
                new Point(input.nextInt() + OFFSET, input.nextInt() + OFFSET),
                new Point(input.nextInt() + OFFSET, input.nextInt() + OFFSET)
        );
        first.fillMap(map);

        Rectangle second = new Rectangle(
                2,
                new Point(input.nextInt() + OFFSET, input.nextInt() + OFFSET),
                new Point(input.nextInt() + OFFSET, input.nextInt() + OFFSET)
        );
        second.fillMap(map);
    }

    private static int area() {
        int maxRow = Integer.MIN_VALUE;
        int minRow = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;

        int[] rowRange = first.getRowRange();
        for (int i = rowRange[0]; i <= rowRange[1]; i++) {
            int[] colRange = first.getColRange();
            for (int j = colRange[0]; j <= colRange[1]; j++) {
                if (map[i][j] == 1) {
                    maxRow = Math.max(maxRow, i);
                    minRow = Math.min(minRow, i);
                    maxCol = Math.max(maxCol, j);
                    minCol = Math.min(minCol, j);
                }
            }
        }

        if (maxRow == Integer.MIN_VALUE) {
            return 0;
        }

        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }

    private static class Rectangle {
        private int no;
        private Point leftBottom;
        private Point rightTop;

        public Rectangle(int no, Point leftBottom, Point rightTop) {
            this.no = no;
            this.leftBottom = leftBottom;
            this.rightTop = rightTop;
        }

        public void fillMap(int[][] map) {
            for (int i = leftBottom.row; i < rightTop.row; i++) {
                for (int j = leftBottom.col; j < rightTop.col; j++) {
                    map[i][j] += no;
                }
            }
        }

        public int[] getRowRange() {
            return new int[] { leftBottom.row, rightTop.row };
        }

        public int[] getColRange() {
            return new int[] { leftBottom.col, rightTop.col };
        }
    }

    private static class Point {
        private int row;
        private int col;

        public Point(int col, int row) {
            this.row = row;
            this.col = col;
        }
    }

}
