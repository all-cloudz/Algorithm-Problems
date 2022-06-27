package problem_2252;

import java.io.*;
import java.util.*;

public class Problem_2252_TraversePostOrder {
    private static class Graph {
        private List<Integer>[] adjList;
        private int size;

        public Graph(int size) {
            this.size = size;
            adjList = new List[size + 1];

            for (int i = 1; i <= size; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addDirectedEdge(int head, int tail) {
            adjList[head].add(tail);
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

        Stack<Integer> sorted = sortTopologically(graph);
        while (!sorted.isEmpty()) {
            answer.append(sorted.pop()).append(' ');
        }

        System.out.print(answer);
    }

    private static Stack<Integer> sortTopologically(Graph graph) {
        boolean[] discovered = new boolean[graph.size + 1];
        Stack<Integer> sorted = new Stack<>();

        for (int i = 1; i <= graph.size; i++) {
            if (!discovered[i]){
                sortTopologically(graph, i, discovered, sorted);
            }
        }

        return sorted;
    }

    private static void sortTopologically(Graph graph, int cur, boolean[] discovered, Stack<Integer> sorted) {
        discovered[cur] = true;

        for (Integer next : graph.adjList[cur]) {
            if (!discovered[next]) {
                sortTopologically(graph, next, discovered, sorted);
            }
        }

        sorted.push(cur);
    }
}
