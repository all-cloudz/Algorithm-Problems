package backtracking.problem_2967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_2967 {
    private static class Rectangle {
        private int left;
        private int up;
        private int right;
        private int down;
        private int size;

        public Rectangle(int up, int left, int down, int right) {
            this.up = up;
            this.left = left;
            this.down = down;
            this.right = right;
            this.size = this.cntX();
        }

        public Rectangle(int up, int left, int down, int right, int size) {
            this.up = up;
            this.left = left;
            this.down = down;
            this.right = right;
            this.size = size;
        }

        public static int size(Stack<Rectangle> rectangles) {
            Rectangle r1 = rectangles.get(0);
            Rectangle r2 = rectangles.get(1);

            Rectangle intersection = intersect(r1, r2);

            try {
                return r1.size + r2.size - intersection.size;
            } catch (NullPointerException e) {
                return r1.size + r2.size;
            }
        }

        public static Rectangle intersect(Rectangle r1, Rectangle r2) {
            int up = Math.max(r1.up, r2.up);
            int left = Math.max(r1.left, r2.left);
            int down = Math.min(r1.down, r2.down);
            int right = Math.min(r1.right, r2.right);

            if (left > right || up > down) {
                return null;
            }

            return new Rectangle(up, left, down, right);
        }

        public boolean isRectangle() {
            return cntX() == size;
        }

        public int cntX() {
            return cumulativeX[down][right] - cumulativeX[down][left - 1] - cumulativeX[up - 1][right] + cumulativeX[up -1][left -1];
        }

        @Override
        public String toString() {
            return up + " " + left + " " + (int) Math.sqrt(size) + "\n";
        }
    }

    private static final int INF = 100 * 100;

    private static StringBuilder answer = new StringBuilder();
    private static int R;
    private static int C;
    private static char[][] site;
    private static int[][] cumulativeX;
    private static int totalSize;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        site = new char[R + 2][C + 2];
        cumulativeX = new int[R + 2][C + 2];

        for (int i = 1; i <= R; i++) {
            String line = input.readLine();

            for (int j = 1; j <= C; j++) {
                site[i][j] = line.charAt(j - 1);

                if (site[i][j] == 'x') {
                    totalSize++;
                    cumulativeX[i][j] = cumulativeX[i - 1][j] + cumulativeX[i][j - 1] - cumulativeX[i - 1][j - 1] + 1;
                    continue;
                }

                cumulativeX[i][j] = cumulativeX[i - 1][j] + cumulativeX[i][j - 1] - cumulativeX[i - 1][j - 1];
            }
        }

        findBuilding(new Stack<>());
    }

    public static void findBuilding(Stack<Rectangle> rectangles) {
        if (rectangles.size() == 2) {
            if (Rectangle.size(rectangles) != totalSize) {
                return;
            }

            for (Rectangle cur : rectangles) {
                answer.append(cur.toString());
            }

            System.out.println(answer);
            System.exit(0);
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (site[i][j] != 'x') {
                    continue;
                }

                int len = 0;
                while (true) {
                    int row = i + len;
                    int col = j + len;

                    if (site[row][j] != 'x' || site[i][col] != 'x') {
                        break;
                    }

                    len++;
                }

                while (--len >= 0) {
                    Rectangle cur = new Rectangle(i, j, i + len, j + len, (int) Math.pow(len + 1, 2));

                    if (!cur.isRectangle()) {
                        continue;
                    }

                    rectangles.push(cur);
                    findBuilding(rectangles);
                    rectangles.pop();
                }
            }
        }
    }
}
