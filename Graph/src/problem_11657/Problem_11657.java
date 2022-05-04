package problem_11657;

import java.io.*;
import java.util.*;

public class Problem_11657 {
    private static class Edge {
        private int head;
        private int tail;
        private int weight;

        public Edge(int head, int tail, int weight) {
            this.head = head;
            this.tail = tail;
            this.weight = weight;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        Edge[] edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            edges[i] = new Edge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        findShortestDist(N, edges, 1);
        System.out.print(answer);
    }

    private static void findShortestDist(int vertexNum, Edge[] edges, int depart) {
        HashMap<Integer, Long> dists = new HashMap<>();
        final long INF = Long.MAX_VALUE;

        for (int i = 1; i <= vertexNum; i++) {
            dists.put(i, INF);
        }

        dists.put(depart, 0L);

        for (int i = 0; i < vertexNum; i++) {
            for (Edge edge : edges) {
                int head = edge.head;
                int tail = edge.tail;

                if (dists.get(head) == INF) {
                    continue;
                }

                long newDist = dists.get(head) + edge.weight; // underflow 방지를 위해 자료형을 long으로 설정

                if (dists.get(tail) <= newDist) {
                    continue;
                }

                if (i == vertexNum - 1) {
                    answer.append(-1);
                    return;
                }

                dists.put(tail, newDist);
            }
        }

        for (int i = 2; i <= vertexNum; i++) {
            long minDist = (dists.get(i) == INF) ? -1 : dists.get(i);
            answer.append(minDist).append('\n');
        }
    }
}