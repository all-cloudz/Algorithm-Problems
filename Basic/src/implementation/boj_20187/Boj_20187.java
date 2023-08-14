package implementation.boj_20187;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_20187 {

    private static final String DOWN = "D";
    private static final String UP = "U";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";

    private static int k;
    private static int[][] paper;
    private static String[] operators;
    private static int holePosition;

    public static void main(String[] args) throws IOException {
        init();
        foldRecursively(0, new Square(0, 0, (1 << k) - 1, (1 << k) - 1));
        printPaper();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(input.readLine());
        final int size = 1 << k;
        paper = new int[size][size];
        operators = input.readLine().split(" ");
        holePosition = Integer.parseInt(input.readLine());
    }

    private static void foldRecursively(final int index, final Square square) {
        if (square.area() == 1) {
            final Point topLeft = square.topLeft;
            paper[topLeft.row][topLeft.col] = holePosition;
            return;
        }

        final Square folded = square.fold(operators[index]);
        foldRecursively(index + 1, folded);
        holeSymmetrically(operators[index], folded);
    }

    private static void holeSymmetrically(final String operator, final Square folded) {
        switch (operator) {
            case DOWN:
                holeSymmetricallyOverRow(operator, folded, folded.topLeft.row - 1);
                return;
            case UP:
                holeSymmetricallyOverRow(operator, folded, folded.bottomRight.row);
                return;
            case RIGHT:
                holeSymmetricallyOverCol(operator, folded, folded.topLeft.col - 1);
                return;
            case LEFT:
                holeSymmetricallyOverCol(operator, folded, folded.bottomRight.col);
                return;
            default:
                throw new RuntimeException("이 코드는 실행될 수 없습니다.");
        }
    }

    private static void holeSymmetricallyOverRow(final String operator, final Square folded, final int foldingLine) {
        for (int i = folded.topLeft.row; i <= folded.bottomRight.row; i++) {
            for (int j = folded.topLeft.col; j <= folded.bottomRight.col; j++) {
                paper[(2 * foldingLine - i) + 1][j] = convert(operator, paper[i][j]);
            }
        }
    }

    private static void holeSymmetricallyOverCol(final String operator, final Square folded, final int foldingLine) {
        for (int i = folded.topLeft.row; i <= folded.bottomRight.row; i++) {
            for (int j = folded.topLeft.col; j <= folded.bottomRight.col; j++) {
                paper[i][(2 * foldingLine - j) + 1] = convert(operator, paper[i][j]);
            }
        }
    }

    private static int convert(final String operator, final int holePosition) {
        if (DOWN.equals(operator) || UP.equals(operator)) {
            return (holePosition + 2) % 4;
        }

        return (-holePosition + 5) % 4;
    }

    private static void printPaper() {
        StringBuilder answer = new StringBuilder();
        for (int size = paper.length, i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer.append(paper[i][j]).append(' ');
            }

            answer.append('\n');
        }
        System.out.println(answer);
    }

    private static class Square {
        private Point topLeft;
        private Point bottomRight;

        public Square(final int topLeftRow, final int topLeftCol, final int bottomRightRow, final int bottomRightCol) {
            this(new Point(topLeftRow, topLeftCol), new Point(bottomRightRow, bottomRightCol));
        }

        public Square(final Point topLeft, final Point bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }

        public int area() {
            return (bottomRight.row - topLeft.row + 1) * (bottomRight.col - topLeft.col + 1);
        }

        public Square fold(final String operator) {
            switch (operator) {
                case DOWN:
                    return new Square(rowAverage() + 1, topLeft.col, bottomRight.row, bottomRight.col);
                case UP:
                    return new Square(topLeft.row, topLeft.col, rowAverage(), bottomRight.col);
                case RIGHT:
                    return new Square(topLeft.row, colAverage() + 1, bottomRight.row, bottomRight.col);
                case LEFT:
                    return new Square(topLeft.row, topLeft.col, bottomRight.row, colAverage());
                default:
                    throw new RuntimeException("이 코드는 실행될 수 없습니다.");
            }
        }

        private int rowAverage() {
            return (topLeft.row + bottomRight.row) >> 1;
        }

        private int colAverage() {
            return (topLeft.col + bottomRight.col) >> 1;
        }
    }

    private static class Point {
        private int row;
        private int col;

        public Point(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }
}
