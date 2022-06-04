package problem_1504;

import java.io.*;
import java.util.*;

public class Problem_1504_AdjList {
    private static class Vertex {
        private int data;
        private int weight;

        public Vertex(int data, int weight) {
            this.data = data;
            this.weight = weight;
        }
    }

    private static class Graph {
        private HashMap<Integer, List<Vertex>> adjList;
        private int size;

        public Graph(int vertexNum) {
            this.size = vertexNum;
            adjList = new HashMap<>();

            for (int i = 1; i <= vertexNum; i++) {
                adjList.put(i, new ArrayList<>());
            }
        }

        public void addCompleteEdge(int vertex1, int vertex2, int weight) {
            adjList.get(vertex1).add(new Vertex(vertex2, weight));
            adjList.get(vertex2).add(new Vertex(vertex1, weight));
        }
    }

    private static final int INF = 1000 * 200000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int V = Integer.parseInt(tokenizer.nextToken());
        Graph graph = new Graph(V);

        final int E = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int vertex1 = Integer.parseInt(tokenizer.nextToken());
            int vertex2 = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            graph.addCompleteEdge(vertex1, vertex2, weight);
        }

        tokenizer = new StringTokenizer(input.readLine());

        int waypoint1 = Integer.parseInt(tokenizer.nextToken());
        int waypoint2 = Integer.parseInt(tokenizer.nextToken());

        int answer = Math.min(dijkstra(graph, 1, waypoint1) + dijkstra(graph, waypoint2, V), dijkstra(graph, 1, waypoint2) + dijkstra(graph, waypoint1, V)) + dijkstra(graph, waypoint1, waypoint2);

        System.out.print(answer);
    }

    private static int dijkstra(Graph graph, int depart, int arrive) {
        int[] dists = new int[graph.size + 1];
        Arrays.fill(dists, INF);
        dists[depart] = 0;

        PriorityQueue<Vertex> open = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        open.add(new Vertex(depart, 0));

        while (!open.isEmpty()) {
            Vertex cur = open.poll();

            if (cur.data == arrive) {
                break;
            }

            if (cur.weight > dists[cur.data]) {
                continue;
            }

            for (Vertex nxt : graph.adjList.get(cur.data)) {
                int minDist = dists[nxt.data];
                int newDist = dists[cur.data] + nxt.weight;

                if (minDist > newDist) {
                    dists[nxt.data] = newDist;
                    open.add(new Vertex(nxt.data, newDist));
                }
            }
        }

        if (dists[arrive] == INF) {
            System.out.print(-1);
            System.exit(0);
        }

        return dists[arrive];
    }
}
