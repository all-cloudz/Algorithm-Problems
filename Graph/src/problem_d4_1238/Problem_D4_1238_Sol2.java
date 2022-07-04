package problem_d4_1238;

import java.io.*;
import java.util.*;

public class Problem_D4_1238_Sol2 {
    private static class Graph {
        private HashMap<Integer, Set<Integer>> adjList;

        public Graph() {
            adjList = new HashMap<>();
        }

        public void addDirectedEdge(int head, int tail) {
            if (!adjList.containsKey(head)) {
                adjList.put(head, new HashSet<>());
            }

            if (!adjList.containsKey(tail)) {
                adjList.put(tail, new HashSet<>());
            }

            adjList.get(head).add(tail);
        }
    }

    private static class Person implements Comparable<Person> {
        private int label;
        private int level;

        public Person(int label, int level) {
            this.label = label;
            this.level = level;
        }

        @Override
        public int compareTo(Person o) {
            if (this.level == o.level) {
                return o.label - this.label;
            }

            return o.level - this.level;
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            final int dataLength = Integer.parseInt(tokenizer.nextToken());
            final int start = Integer.parseInt(tokenizer.nextToken());

            Graph network = new Graph();

            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < dataLength / 2; j++) {
                int head = Integer.parseInt(tokenizer.nextToken());
                int tail = Integer.parseInt(tokenizer.nextToken());

                network.addDirectedEdge(head, tail);
            }

            answer.append(String.format("#%d %d%n", i, lastPerson(network, start)));
        }

        System.out.print(answer);
    }

    private static int lastPerson(Graph network, int start) {
        PriorityQueue<Person> persons = new PriorityQueue<>();

        HashSet<Integer> discovered = new HashSet<>();
        Queue<Integer> vertices = new LinkedList<>();

        discovered.add(start);
        vertices.offer(start);

        int level = 0;
        while (!vertices.isEmpty()) {
            int size = vertices.size();
            for (int i = 0; i < size; i++) {
                int cur = vertices.poll();
                persons.offer(new Person(cur, level));

                for (int next : network.adjList.get(cur)) {
                    if (discovered.contains(next)) {
                        continue;
                    }

                    discovered.add(next);
                    vertices.add(next);
                }
            }

            level++;
        }

        return persons.poll().label;
    }
}
