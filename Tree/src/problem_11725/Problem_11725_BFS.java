package problem_11725;

import java.io.*;
import java.util.*;

public class Problem_11725_BFS {
    private static class Vertex {
        private int data;
        private List<Vertex> adjList;

        public Vertex(int data) {
            this.data = data;
            adjList = new ArrayList<>();
        }

        public static void addCompleteEdge(Vertex[] graph, int head, int tail) {
            graph[head].adjList.add(graph[tail]);
            graph[tail].adjList.add(graph[head]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int V = Integer.parseInt(input.readLine());

        Vertex[] graph = new Vertex[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 1; i < V; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());

            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());

            Vertex.addCompleteEdge(graph, head, tail);
        }

        bfs(graph, 1);
    }

    private static void bfs(Vertex[] graph, int start) {
        int[] parent = new int[graph.length];
        Queue<Vertex> vertices = new LinkedList<>();

        parent[start] = start;
        vertices.offer(graph[start]);

        while (!vertices.isEmpty()) {
            Vertex cur = vertices.poll();

            for (Vertex neighbor : cur.adjList) {
                int next = neighbor.data;

                if (parent[next] == 0) {
                    parent[next] = cur.data;
                    vertices.offer(neighbor);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 2; i < graph.length; i++) {
            answer.append(parent[i]).append('\n');
        }

        System.out.print(answer);
    }
}
