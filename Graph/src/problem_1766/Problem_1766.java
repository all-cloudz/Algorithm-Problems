package problem_1766;

import java.io.*;
import java.util.*;

public class Problem_1766 {
    private static class Graph {
        private HashMap<Integer, List<Integer>> adjList;
        private int[] indegrees;
        private int size;

        public Graph(int N) {
            this.adjList = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                adjList.put(i, new ArrayList<>());
            }

            this.indegrees = new int[N + 1];
            this.size = N;
        }

        public void addDirectedEdge(int head, int tail) {
            adjList.get(head).add(tail);
            indegrees[tail]++;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        Graph problems = new Graph(N);

        int M = Integer.parseInt(tokenizer.nextToken());
        while (M-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());

            problems.addDirectedEdge(head, tail);
        }

        sortTopologically(problems);
        System.out.print(answer);
    }

    private static void sortTopologically(Graph problems) {
        PriorityQueue<Integer> vertices = new PriorityQueue<>();

        for (int i = 1; i <= problems.size; i++) {
            if (problems.indegrees[i] == 0) {
                vertices.offer(i);
            }
        }

        for (int i = 1; i <= problems.size; i++) {
//            if (vertices.isEmpty()) {
//                System.out.print("Has Cycle");
//                System.exit(-1);
//            }

            int cur = vertices.poll();
            answer.append(cur).append(' ');

            for (int nxt : problems.adjList.get(cur)) {
                if (--problems.indegrees[nxt] == 0) {
                    vertices.offer(nxt);
                }
            }
        }
    }
}
