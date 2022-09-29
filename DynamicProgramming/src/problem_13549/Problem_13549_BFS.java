package problem_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_13549_BFS {

    private static class Pair implements Comparable<Pair> {
        private int index;
        private int moveCnt;

        public Pair(int index, int moveCnt) {
            this.index = index;
            this.moveCnt = moveCnt;
        }

        @Override
        public int compareTo(Pair o) {
            return this.moveCnt - o.moveCnt;
        }
    }

    private static final int MAX = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        PriorityQueue<Pair> open = new PriorityQueue<>();
        boolean[] visited = new boolean[MAX + 1];
        
        open.offer(new Pair(N, 0));

        while (!open.isEmpty()) {
            Pair cur = open.poll();
            
            if (visited[cur.index]) {
                continue;
            }

            visited[cur.index] = true;

            if (cur.index == K) {
                System.out.println(cur.moveCnt);
                return;
            }

            if (cur.index + 1 <= MAX && !visited[cur.index + 1]) {
                open.offer(new Pair(cur.index + 1, cur.moveCnt + 1));
            }

            if (cur.index - 1 > 0 && !visited[cur.index - 1]) {
                open.offer(new Pair(cur.index - 1, cur.moveCnt + 1));
            }

            if (cur.index << 1 <= MAX && !visited[cur.index << 1]) {
                open.offer(new Pair(cur.index << 1, cur.moveCnt));
            }
        }
    }

}
