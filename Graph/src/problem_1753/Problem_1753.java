package problem_1753;

import java.io.*;
import java.util.*;

public class Problem_1753 {
    private static class Vertex implements Comparable<Vertex> {
        private int data;
        private int weight;

        public Vertex(int data) {
            this(data, 0);
        }

        public Vertex(int data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Vertex)) {
                return false;
            }

            Vertex that = (Vertex) o;

            return (this.hashCode() == that.hashCode()) && (this.data == that.data) && (this.weight == that.weight);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + this.data + this.weight;
            return hash;
        }
    }

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

    private static class Graph {
        private int size;
        private HashMap<Vertex, List<Vertex>> adjList = new HashMap<>(); // 인접리스트 - 다익스트라에 사용
        private List<Edge> edgeList = new ArrayList<>(); // 간접리스트 - 벨만포드에 사용

        public Graph(int num) {
            size = num;

            for (int i = 1; i <= num; i++) {
                this.adjList.put(new Vertex(i), new ArrayList<>());
            }
        }

        public void addDirectedEdge(int head, int tail, int weight) {
            adjList.get(new Vertex(head)).add(new Vertex(tail, weight));
            edgeList.add(new Edge(head, tail, weight));
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        final int V = Integer.parseInt(tokenizer.nextToken());
        Graph graph = new Graph(V);

        final int E = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(input.readLine());
        final int K = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            graph.addDirectedEdge(head, tail, weight);
        }

        dijkstra(graph, K);
        System.out.print(answer);
    }

    private static void dijkstra(Graph graph, int depart) {
        HashMap<Integer, Integer> dists = new HashMap<>();

        // 1. 시작 지점을 제외하고 시작 지점으로부터의 거리를 INF로 초기화
        for (int i = 1; i <= graph.size; i++) {
            dists.put(i, Integer.MAX_VALUE);
        }
        dists.put(depart, 0);

        // 2. 우선순위 큐에 시작 지점을 담는다. 이때, weight는 시작 지점으로부터의 거리를 의미한다.
        PriorityQueue<Vertex> vertices = new PriorityQueue<>();
        vertices.add(new Vertex(depart, 0));

        // 3. 우선순위 큐가 빌때까지 다음을 반복한다.
        while (!vertices.isEmpty()) {
            Vertex current = vertices.poll();

            // 사전에 한 번이라도 큐에서 제거되었다면 반드시 최단 거리를 저장하고 있다.
            if (current.weight > dists.get(current.data)) {
                continue;
            }

            // 우선순위 큐에서 제거한 정점의 이웃에 대하여 다음을 반복한다.
            for (Vertex next : graph.adjList.get(new Vertex(current.data))) {
                // 이웃까지의 거리를 더 짧은 것으로 갱신한다.
                int minDist = dists.get(next.data);
                int newDist = dists.get(current.data) + next.weight;

                // 한 번이라도 큐에서 제거되었다면 이미 최단 거리를 저장하고 있다.
                if (minDist > newDist) {
                    dists.put(next.data, newDist);
                    vertices.add(new Vertex(next.data, newDist));
                }
            }
        }

        for (int i = 1; i <= graph.size; i++) {
            int dist = dists.get(i);

            if (dist == Integer.MAX_VALUE) {
                answer.append("INF").append('\n');
                continue;
            }

            answer.append(dist).append('\n');
        }
    }
}
