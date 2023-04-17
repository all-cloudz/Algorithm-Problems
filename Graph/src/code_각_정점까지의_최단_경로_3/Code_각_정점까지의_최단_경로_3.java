package code_각_정점까지의_최단_경로_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Code_각_정점까지의_최단_경로_3 {

    private static int n;
    private static Map<Integer, List<Vertex>> adjLists = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        for (int i = 1; i <= n; i++) {
            adjLists.put(i, new ArrayList<>());
        }

        int m = Integer.parseInt(tokenizer.nextToken());
        while (m-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());
            adjLists.get(from).add(new Vertex(to, weight));
        }

        StringBuilder answer = new StringBuilder();
        getShortestDists(1)
                .stream()
                .skip(2)
                .forEach(dist -> answer.append((dist == Integer.MAX_VALUE) ? -1 : dist).append('\n'));
        System.out.println(answer);
    }

    private static List<Integer> getShortestDists(int start) {
        PriorityQueue<Vertex> open = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        List<Integer> dists = new ArrayList<>();

        open.offer(new Vertex(start, 0));
        for (int i = 0; i <= n; i++) {
            dists.add(Integer.MAX_VALUE);
        }
        dists.set(start, 0);

        while (!open.isEmpty()) {
            Vertex cur = open.poll();

            if (cur.weight > dists.get(cur.label)) {
                continue;
            }

            for (Vertex next : adjLists.get(cur.label)) {
                int minDist = dists.get(next.label);
                int newDist = next.weight + dists.get(cur.label);

                if (minDist > newDist) {
                    open.offer(new Vertex(next.label, newDist));
                    dists.set(next.label, newDist);
                }
            }
        }

        return dists;
    }

    private static class Vertex {
        private int label;
        private int weight;

        public Vertex(int label, int weight) {
            this.label = label;
            this.weight = weight;
        }
    }

}
