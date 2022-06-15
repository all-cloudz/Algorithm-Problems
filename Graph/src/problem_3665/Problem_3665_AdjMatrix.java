package problem_3665;

import java.io.*;
import java.util.*;

public class Problem_3665_AdjMatrix {
    private static class Graph {
        private boolean[][] adjMatrix;
        private int[] inDegree;
        private int size;

        public Graph(int[] ranking) {
            this.size = ranking.length - 1;

            adjMatrix = new boolean[size + 1][size + 1];
            inDegree = new int[size + 1];

            for (int i = 1; i <= size; i++) {
                for (int j = i + 1; j <= size; j++) {
                    addDirectedEdge(ranking[i], ranking[j]);
                }
            }
        }

        public void addDirectedEdge(int head, int tail) {
            adjMatrix[head][tail] = true;
            inDegree[tail]++;
        }

        public void editDirectedEdge(int head, int tail) {
            if (adjMatrix[head][tail]) {
                int tmp = head;
                head = tail;
                tail = tmp;
            }

            adjMatrix[tail][head] = false;
            inDegree[head]--;

            addDirectedEdge(head, tail);
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        while(T-- > 0) {
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
                vertices.offer(i);
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

            for (int next = 1; next <= graph.size; next++) {
                if (!graph.adjMatrix[cur][next]) {
                    continue;
                }

                if (--graph.inDegree[next] == 0) {
                    vertices.offer(next);
                }
            }
        }

        answer.append(sorted);
    }

    // 이 문제에서는 상황을 잘 생각해보면 다음과 같이 위상정렬을 굳이 사용하지 않아도 풀 수 있다.
    private static void sort(Graph graph) {
        Map<Integer, Integer> map = new HashMap<>(); // key : inDegree, value : vertex
        StringBuilder sorted = new StringBuilder();

        for (int i = 1; i <= graph.size; i++) {
            map.put(graph.inDegree[i], i);
        }

        for (int i = 0; i < graph.size; i++) {
            if (!map.containsKey(i)) {
                answer.append("IMPOSSIBLE");
                return;
            }

            int cur = map.get(i);
            sorted.append(cur).append(' ');
        }

        answer.append(sorted);
    }
}
