package implementation.code_잔해물을_덮기_위한_사각형의_최소_넓이;

import java.util.Scanner;

public class Code_잔해물을_덮기_위한_사각형의_최소_넓이_Sol1 {

    private static int[][] map;
    private static int OFFSET = 1000;
    private static int x11, y11, x12, y12;
    private static int x21, y21, x22, y22;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        map = new int[2001][2001];
        x11 = input.nextInt() + OFFSET;
        y11 = input.nextInt() + OFFSET;
        x12 = input.nextInt() + OFFSET;
        y12 = input.nextInt() + OFFSET;
        filRec(x11, x12, y11, y12, 1);

        x21 = input.nextInt() + OFFSET;
        y21 = input.nextInt() + OFFSET;
        x22 = input.nextInt() + OFFSET;
        y22 = input.nextInt() + OFFSET;
        filRec(x21, x22, y21, y22, 2);

        if (isPenetrated()) {
            System.out.println((x12 - x11) * (y12 - y11));
            return;
        }

        System.out.println(row() * col());
    }

    private static void filRec(int x1, int x2, int y1, int y2, int n) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                map[i][j] += n;
            }
        }
    }

    private static boolean isPenetrated() {
        return isPenetratedToRow() || isPenetratedToCol();
    }

    private static boolean isPenetratedToRow() {
        return y11 < y21 && y12 > y22 && x11 >= x21 && x12 <= x22;
    }

    private static boolean isPenetratedToCol() {
        return y11 >= y21 && y12 <= y22 && x11 < x21 && x12 > x22;
    }

    private static int row() {
        int row = 0;
        for (int i = 0; i < 2001; i++) {
            int max = 0;
            for (int j = 0; j < 2001; j++) {
                if (map[j][i] == 1) {
                    max++;
                }
            }
            if (max > row) {
                row = max;
            }
        }
        return row;
    }

    private static int col() {
        int col = 0;
        for (int i = 0; i < 2001; i++) {
            int max = 0;
            for (int j = 0; j < 2001; j++) {
                if (map[i][j] == 1) {
                    max++;
                }
            }
            if (max > col) {
                col = max;
            }
        }

        return col;
    }

}
