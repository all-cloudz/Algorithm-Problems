package problem_1504;

import java.io.*;
import java.util.*;

public class Problem_1504_AdjMatrix {
    private static class Vertex {
        private int data;
        private int weight;

        public Vertex(int data, int weight) {
            this.data = data;
            this.weight = weight;
        }
    }

    private static class Graph {
        private int[][] adjMatrix;
        private int size;

        public Graph(int vertexNum) {
            this.size = vertexNum;
            adjMatrix = new int[vertexNum + 1][vertexNum + 1];
        }

        public void addCompleteEdge(int vertex1, int vertex2, int weight) {
            adjMatrix[vertex1][vertex2] = weight;
            adjMatrix[vertex2][vertex1] = weight;
        }
    }

    private static final int INF = 1000 * 200000 + 1;

    private static int V;
    private static Graph graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        V = Integer.parseInt(tokenizer.nextToken());
        graph = new Graph(V);

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

        int[] distFrom1 = dijkstra(1);
        int[] distFromV = dijkstra(V);

        int answer = Math.min(distFrom1[waypoint1] + distFromV[waypoint2], distFrom1[waypoint2] + distFromV[waypoint1]) + dijkstra(waypoint1, waypoint2);

        if (answer >= INF) {
            System.out.print(-1);
            return;
        }

        System.out.print(answer);
    }

    private static int dijkstra(int depart, int arrive) {
        int[] dists = new int[V + 1];
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

            for (int i = 1; i <= V; i++) {
                if (graph.adjMatrix[cur.data][i] == 0) {
                    continue;
                }

                int minDist = dists[i];
                int newDist = dists[cur.data] + graph.adjMatrix[cur.data][i];

                if (minDist > newDist) {
                    dists[i] = newDist;
                    open.add(new Vertex(i, newDist));
                }
            }
        }

        return dists[arrive];
    }

    private static int[] dijkstra(int depart) {
        int[] dists = new int[graph.size + 1];
        Arrays.fill(dists, INF);
        dists[depart] = 0;

        PriorityQueue<Vertex> open = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        open.add(new Vertex(depart, 0));

        while (!open.isEmpty()) {
            Vertex cur = open.poll();

            if (cur.weight > dists[cur.data]) {
                continue;
            }

            for (int i = 1; i <= V; i++) {
                if (graph.adjMatrix[cur.data][i] == 0) {
                    continue;
                }

                int minDist = dists[i];
                int newDist = dists[cur.data] + graph.adjMatrix[cur.data][i];

                if (minDist > newDist) {
                    dists[i] = newDist;
                    open.add(new Vertex(i, newDist));
                }
            }
        }

        return dists;
    }
}
