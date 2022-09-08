package problem_tree2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Problem_Tree2_12 {

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        Point[] points = new Point[n];
        TreeSet<Integer> coordinate = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            points[i] = new Point(x, y);
            coordinate.add(x);
            coordinate.add(y);
        }

        Map<Integer, Integer> compressionOfCoordinate = new HashMap<>();
        int cnt = 1;
        for (int num : coordinate) {
            compressionOfCoordinate.put(num, cnt++);
        }

        int size = (n << 1) + 2;
        int[][] prefixSum = new int[size][size];
        for (Point cur : points) {
            int newX = compressionOfCoordinate.get(cur.x);
            int newY = compressionOfCoordinate.get(cur.y);
            prefixSum[newX][newY]++;
        }

        fillTable(prefixSum);

        while (q-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            int newX1 = getLBD(coordinate, compressionOfCoordinate, x1);
            int newY1 = getLBD(coordinate, compressionOfCoordinate, y1);
            int newX2 = getUBD(coordinate, compressionOfCoordinate, x2);
            int newY2 = getUBD(coordinate, compressionOfCoordinate, y2);
            answer.append(getSum(prefixSum, newX1, newY1, newX2, newY2)).append('\n');
        }

        System.out.println(answer);
    }

    private static void fillTable(int[][] prefixSum) {
        for (int rowSize = prefixSum.length, i = 1; i < rowSize; i++) {
            for (int colSize = prefixSum[i].length, j = 1; j < colSize; j++) {
                prefixSum[i][j] += prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }
    }

    private static int getLBD(TreeSet<Integer> coordinate, Map<Integer, Integer> compressionOfCoordinate, int num) {
        Integer lowerBound = coordinate.ceiling(num);
        return (lowerBound == null) ? coordinate.size() + 1 : compressionOfCoordinate.get(lowerBound);
    }

    private static int getUBD(TreeSet<Integer> coordinate, Map<Integer, Integer> compressionOfCoordinate, int num) {
        Integer upperBound = coordinate.floor(num);
        return (upperBound == null) ? 0 : compressionOfCoordinate.get(upperBound);
    }

    private static int getSum(int[][] prefixSum, int x1, int y1, int x2, int y2) {
        return prefixSum[x2][y2] - prefixSum[x1 - 1][y2] - prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 - 1];
    }

}
