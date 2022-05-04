package problem_1197;

import java.io.*;
import java.util.*;

public class Problem_1197 {
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

    private static class DisjointSet {
        // HashMap으로 각 정점을 유일한 노드로 하는 트리를 생성하면 메모리 초과가 생긴다.
        private int[] trees;
        private int[] sizes;

        public DisjointSet(int V) {
            trees = new int[V + 1];
            sizes = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                trees[i] = i;
                sizes[i] = 1;
            }
        }

        public int find(int node) {
            int parent = trees[node];

            if (parent == node) {
                return node;
            }

            return find(parent);
        }

        public boolean union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2) {
                return false;
            }

            int parent = trees[root1];
            int child = trees[root2];

            if (sizes[parent] < sizes[child]) {
                int tmp = parent;
                parent = child;
                child = tmp;
            }

            trees[child] = parent;
            sizes[parent] += sizes[child];

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        int V = Integer.parseInt(tokenizer.nextToken());

        int E = Integer.parseInt(tokenizer.nextToken());
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            edges.add(new Edge(head, tail, weight));
        }

        System.out.print(kruskal(V, edges));
    }

    private static int kruskal(int V, List<Edge> edges) {
        DisjointSet set = new DisjointSet(V);
        List<Edge> mst = new ArrayList<>();

        Collections.sort(edges, (a, b) -> a.weight - b.weight);

        while (!edges.isEmpty() && mst.size() < V - 1) {
            Edge edge = edges.remove(0);

            int head = edge.head;
            int tail = edge.tail;

            if (set.union(head, tail)) {
                mst.add(edge);
            }
        }

        int sum = 0;

        for (Edge edge : mst) {
            sum += edge.weight;
        }

        return sum;
    }
}
