package problem_9328;

import java.io.*;
import java.util.*;

public class Problem_9328_Sol2 {
    private static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTION = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    private static StringBuilder answer;
    private static int keys;
    private static HashMap<Integer, List<Point>> doorMap;
    private static int h;
    private static int w;
    private static char[][] map;
    private static boolean[][] discovered;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            keys = 0;
            doorMap = new HashMap<>();
            for (int i = 0; i < 26; i++) {
                doorMap.put(1 << i, new ArrayList<>());
            }

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            h = Integer.parseInt(tokenizer.nextToken());
            w = Integer.parseInt(tokenizer.nextToken());
            map = new char[h + 2][w + 2];
            discovered = new boolean[h + 2][w + 2];
            cnt = 0;

            for (int j = 0; j < w + 2; j++) {
                map[0][j] = '.';
                map[h + 1][j] = '.';
            }

            for (int i = 1; i <= h; i++) {
                String str = input.readLine();

                for (int j = 0; j < w + 2; j++) {
                    if (j == 0 || j == w + 1) {
                        map[i][j] = '.';
                        continue;
                    }

                    map[i][j] = str.charAt(j - 1);

                    if (i != 1 && i != h && j != 1 && j != w) {
                        continue;
                    }

                    if (map[i][j] == '$') {
                        cnt++;
                        map[i][j] = '.';
                        continue;
                    }

                    if ('a' <= map[i][j] && map[i][j] <= 'z') {
                        keys |= 1 << (map[i][j] - 'a');
                    }
                }
            }

            char[] keyArr = input.readLine().toCharArray();
            for (char key : keyArr) {
                if (key == '0') {
                    break;
                }

                keys |= 1 << (key - 'a');
            }

            searchDoc();
            answer.append(cnt).append('\n');
        }

        System.out.println(answer);
    }

    private static void searchDoc() {
        Queue<Point> points = new ArrayDeque<>();

        points.offer(new Point(0, 0));
        discovered[0][0] = true;

        while (!points.isEmpty()) {
            Point cur = points.poll();

            for (int i = 0; i < DIRECTION.length; i++) {
                int nextRow = cur.row + DIRECTION[i][0];
                int nextCol = cur.col + DIRECTION[i][1];

                if (nextRow < 0 || nextCol < 0 || nextRow >= h + 2 || nextCol >= w + 2) {
                    continue;
                }

                if (discovered[nextRow][nextCol]) {
                    continue;
                }

                char next = map[nextRow][nextCol];

                if (next == '*') {
                    continue;
                }

                if (next == '$') {
                    cnt++;
                } else if ('a' <= next && next <= 'z') {
                    int key = 1 << (next - 'a');
                    keys |= key;

                    List<Point> doorList = doorMap.get(key);

                    while (!doorList.isEmpty()) {
                        points.offer(doorList.remove(0));
                    }
                } else if ('A' <= next && next <= 'Z') {
                    int door = 1 << (next - 'A');

                    if ((keys & door) != door) {
                        doorMap.get(door).add(new Point(nextRow, nextCol));
                        continue;
                    }
                }

                points.offer(new Point(nextRow, nextCol));
                discovered[nextRow][nextCol] = true;
            }
        }
    }
}
