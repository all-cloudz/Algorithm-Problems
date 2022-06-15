package problem_3665;

import java.io.*;
import java.util.*;

public class Problem_3665_AdjList {
    private static class Graph {
        private Set<Integer>[] adjList;
        private int[] inDegree;
        private int size;

        @SuppressWarnings("unchecked")
        public Graph(int[] ranking) {
            this.size = ranking.length - 1;

            adjList = new Set[size + 1];
            inDegree = new int[size + 1];

            for (int i = 1; i <= size; i++) {
                adjList[i] = new HashSet<>();
            }

            for (int i = 1; i <= size; i++) {
                for (int j = i + 1; j <= size; j++) {
                    addDirectedEdge(ranking[i], ranking[j]);
                }
            }
        }

        public void addDirectedEdge(int head, int tail) {
            adjList[head].add(tail);
            inDegree[tail]++;
        }

        public void editDirectedEdge(int head, int tail) {
            if (adjList[head].contains(tail)) {
                int tmp = head;
                head = tail;
                tail = tmp;
            }

            adjList[tail].remove(head);
            inDegree[head]--;

            addDirectedEdge(head, tail);
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(input.readLine());
            int[] ranking = new int[n + 1];

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int i = 1; i <= n; i++) {
                ranking[i] = Integer.parseInt(tokenizer.nextToken());
            }

            Graph graph = new Graph(ranking);

            int m = Integer.parseInt(input.readLine());
            while (m-- > 0) {
                String[] change = input.readLine().split(" ");
                graph.editDirectedEdge(Integer.parseInt(change[0]), Integer.parseInt(change[1]));
            }

            sortTopologically(graph);
            answer.append('\n');
        }

        System.out.print(answer);
    }

    private static void sortTopologically(Graph graph) {
        Queue<Integer> vertices = new LinkedList<>();
        StringBuilder sorted = new StringBuilder();

        for (int i = 1; i <= graph.size; i++) {
            if (graph.inDegree[i] == 0) {
                vertices.add(i);
            }
        }

        for (int i = 1; i <= graph.size; i++) {
            if (vertices.isEmpty()) {
                answer.append("IMPOSSIBLE");
                return;
            }

            if (vertices.size() > 1) {
                answer.append("?");
                return;
            }

            int cur = vertices.poll();
            sorted.append(cur).append(' ');

            for (int next : graph.adjList[cur]) {
                if (--graph.inDegree[next] == 0) {
                    vertices.add(next);
                }
            }
        }

        answer.append(sorted);
    }
}
