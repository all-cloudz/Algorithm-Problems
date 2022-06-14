package problem_2252;

import java.io.*;
import java.util.*;

public class Problem_2252_Kahn {
    private static class Graph {
        private List<Integer>[] adjList;
        private int[] inDegree;
        private int size;

        @SuppressWarnings("unchecked")
        public Graph(int size) {
            this.size = size;

            inDegree = new int[size + 1];

            adjList = new List[size + 1];
            for (int i = 1; i <= size; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addDirectedEdge(int head, int tail) {
            adjList[head].add(tail);
            inDegree[tail]++;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int N = Integer.parseInt(tokenizer.nextToken());
        Graph graph = new Graph(N);

        final int M = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            final int head = Integer.parseInt(tokenizer.nextToken());
            final int tail = Integer.parseInt(tokenizer.nextToken());

            graph.addDirectedEdge(head, tail);
        }

        sortTopologically(graph);
        System.out.print(answer);
    }

    private static void sortTopologically(Graph graph) {
        Queue<Integer> vertices = new LinkedList<>();

        // 가장 먼저 진입 차수가 0인 정점을 큐에 삽입
        for (int i = 1; i <= graph.size; i++) {
            if (graph.inDegree[i] == 0) {
                vertices.offer(i);
            }
        }

        for (int i = 1; i <= graph.size; i++) {
            if (vertices.isEmpty()) {
                System.out.print("Has Cycle");
                System.exit(1);
            }

            int cur = vertices.poll();
            answer.append(cur).append(' ');

            // cur에서 next로 진입하는 것을 제거했을 때 진입차수가 0이면 큐에 삽입
            for (int next : graph.adjList[cur]) {
                if (--graph.inDegree[next] == 0) {
                    vertices.offer(next);
                }
            }
        }
    }
}
