package problem_9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_9205_BFS {

    private static int n;
    private static int[] house;
    private static int[][] store;
    private static int[] festival;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(input.readLine());
            house = new int[2];
            store = new int[n][2];
            festival = new int[2];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            house = new int[] {Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()) };

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(input.readLine());
                store[i] = new int[] { Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()) };
            }

            tokenizer = new StringTokenizer(input.readLine());
            festival = new int[] {Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()) };

            if (go()) {
                answer.append("happy\n");
                continue;
            }

            answer.append("sad\n");
        }

        System.out.println(answer);
    }

    private static boolean go() {
        Queue<int[]> points = new ArrayDeque<>();
        boolean[] discovered = new boolean[n];

        points.offer(house);
        while (!points.isEmpty()) {
            int[] cur = points.poll();

            if (distance(cur, festival) <= 1000) {
                return true;
            }

            for (int i = 0; i < n; i++) {
                if (discovered[i] || distance(cur, store[i]) > 1000) {
                    continue;
                }

                points.offer(store[i]);
                discovered[i] = true;
            }
        }

        return false;
    }

    private static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

}
