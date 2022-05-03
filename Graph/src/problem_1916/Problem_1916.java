package problem_1916;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Problem_1916 {
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

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(input.readLine());
        HashMap<Integer, List<Vertex>> graph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        final int M = Integer.parseInt(input.readLine());

        for (int i = 0; i < M; i++) {
            String[] tmp = input.readLine().split(" ");
            Integer key = Integer.parseInt(tmp[0]);
            graph.get(key).add(new Vertex(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2])));
        }

        String[] tmp = input.readLine().split(" ");

        System.out.print(findMinCost(graph, Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
    }

    private static int findMinCost(HashMap<Integer, List<Vertex>> graph, int depart, int arrive) {
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> dists = new HashMap<>();

        for (Integer key : graph.keySet()) {
            dists.put(key, Integer.MAX_VALUE);
        }

        dists.put(depart, 0);

        PriorityQueue<Vertex> open = new PriorityQueue<>();
        open.add(new Vertex(depart, 0));

        while (!open.isEmpty()) {
            Vertex current = open.poll();

            if (visited.contains(current.label)) {
                continue;
            }

            for (Vertex neighbor : graph.get(current.label)) {
                int minDist = dists.get(neighbor.label);
                int newDist = dists.get(current.label) + neighbor.weight;

                if (minDist > newDist) {
                    dists.put(neighbor.label, newDist);
                    open.add(new Vertex(neighbor.label, newDist));
                }
            }

            visited.add(current.label);
        }

        return dists.get(arrive);
    }
}
