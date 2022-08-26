package problem_1922;

import java.io.*;
import java.util.*;

public class Problem_1922_Kruskal {
    private static class Edge implements Comparable<Edge> {
        private int vertex1;
        private int vertex2;
        private int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    private static class DisjointSet {
        private int[] parents;

        public DisjointSet(int size) {
            parents = new int[size + 1];

            for (int i = 1; i <= size; i++) {
                parents[i] = i;
            }
        }

        public int find(int child) {
            if (parents[child] == child) {
                return child;
            }

            return parents[child] = find(parents[child]);
        }

        public boolean union(int a, int b) {
            int rootOfA = find(a);
            int rootOfB = find(b);

            if (rootOfA == rootOfB) {
                return false;
            }

            parents[rootOfB] = rootOfA;
            return true;
        }
    }

    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        int M = Integer.parseInt(input.readLine());

        edges = new ArrayList<>();
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (v1 != v2) {
                edges.add(new Edge(v1, v2, weight));
            }
        }

        System.out.println(kruskal(N));
    }

    private static int kruskal(int vertexSize) {
        DisjointSet set = new DisjointSet(vertexSize);
        Collections.sort(edges);

        int minCost = 0;
        int sizeOfMST = 0;

        for (Edge cur : edges) {
            if (sizeOfMST == vertexSize - 1) { // 트리의 간선의 개수만큼 뽑으면 Stop
                return minCost;
            }

            int v1 = cur.vertex1;
            int v2 = cur.vertex2;

            if (set.union(v1, v2)) {
                minCost += cur.weight;
                sizeOfMST++;
            }
        }

        return minCost;
    }
}
