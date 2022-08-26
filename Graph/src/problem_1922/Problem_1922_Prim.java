package problem_1922;

import java.io.*;
import java.util.*;

public class Problem_1922_Prim {
    private static class Vertex implements Comparable<Vertex> {
        private int label;
        private int weight;

        public Vertex(int label, int weight) {
            this.label = label;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }

    private static int N;
    private static List<List<Vertex>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        int M = Integer.parseInt(input.readLine());

        graph = new ArrayList<>(1000);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (v1 != v2) {
                addCompleteEdge(v1, v2, weight);
            }
        }

        System.out.println(prim());
    }

    private static int prim() {
        int[] minCost = new int[N + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[1] = 0;

        PriorityQueue<Vertex> open = new PriorityQueue<>();
        open.offer(new Vertex(1, 0));

        boolean[] visited = new boolean[N + 1];
        int sizeOfMST = 0;
        int totalMinCost = 0;

        while (!open.isEmpty()) {
            if (sizeOfMST == N) { // 정점의 개수만큼 뽑으면 Stop
                return totalMinCost;
            }

            Vertex cur = open.poll();

            if (visited[cur.label]) {
                continue;
            }

            for (Vertex next : graph.get(cur.label)) {
                if (visited[next.label]) {
                    continue;
                }

                if (minCost[next.label] > next.weight) {
                    minCost[next.label] = next.weight;
                    open.offer(next);
                }
            }

            visited[cur.label] = true;
            sizeOfMST++;
            totalMinCost += cur.weight;
        }

        return totalMinCost;
    }

    private static void addCompleteEdge(int v1, int v2, int weight) {
        graph.get(v1).add(new Vertex(v2, weight));
        graph.get(v2).add(new Vertex(v1, weight));
    }
}
