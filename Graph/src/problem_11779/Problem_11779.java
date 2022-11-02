package problem_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_11779 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(input.readLine());
        int m = Integer.parseInt(input.readLine());

        Graph graph = new Graph(n);
        while (m-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());
            graph.addDirectiveEdge(from, to, weight);
        }

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        int departure = Integer.parseInt(tokenizer.nextToken());
        int arrival = Integer.parseInt(tokenizer.nextToken());

        Shortest shortest = new Shortest(graph, departure, arrival);
        answer.append(shortest.dist()).append("\n");

        List<Integer> path = shortest.path();
        answer.append(path.size()).append("\n");

        for (int city : path) {
            answer.append(city).append(" ");
        }

        System.out.println(answer);
    }

}

class Shortest {

    private int dist;
    private List<Integer> path;

    public Shortest(Graph graph, int departure, int arrival) {
        findShortestPath(graph, departure, arrival);
    }

    public int dist() {
        return this.dist;
    }

    public List<Integer> path() {
        return this.path;
    }

    private void findShortestPath(Graph graph, int departure, int arrival) {
        PriorityQueue<Vertex> open = new PriorityQueue<>();
        int[] routes = new int[graph.size() + 1];
        int[] dists = new int[graph.size() + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);

        open.offer(new Vertex(departure, 0));
        routes[departure] = -1;
        dists[departure] = 0;

        while (!open.isEmpty()) {
            Vertex cur = open.poll();
            int curNo = cur.getNo();
            int curDist = cur.getWeight();

            if (curNo == arrival) {
                break;
            }

            if (curDist > dists[curNo]) {
                continue;
            }

            List<Vertex> curAdjList = graph.getAdjList(curNo);
            for (Vertex next : curAdjList) {
                int nextNo = next.getNo();
                int nextDist = next.getWeight();

                int minDist = dists[nextNo];
                int newDist = curDist + nextDist;

                if (minDist > newDist) {
                    open.offer(new Vertex(nextNo, newDist));
                    routes[nextNo] = curNo;
                    dists[nextNo] = newDist;
                }
            }
        }

        this.dist = dists[arrival];
        this.path = getPath(routes, arrival);
    }

    private static LinkedList<Integer> getPath(int[] routes, int cur) {
        LinkedList<Integer> path = new LinkedList<>();

        while (cur != -1) {
            path.addFirst(cur);
            cur = routes[cur];
        }

        return path;
    }

}

class Graph {

    private final List<List<Vertex>> adjLists;
    private final int size;

    public Graph(int n) {
        this.adjLists = new ArrayList<>();
        this.size = n;

        for (int i = 0; i <= n; i++) {
            this.adjLists.add(new ArrayList<>());
        }
    }

    public List<Vertex> getAdjList(int no) {
        return this.adjLists.get(no);
    }

    public void addDirectiveEdge(int from, int to, int weight) {
        List<Vertex> adjList = adjLists.get(from);
        adjList.add(new Vertex(to, weight));
    }

    public int size() {
        return this.size;
    }

}

class Vertex implements Comparable<Vertex> {

    private int no;
    private int weight;

    public Vertex(int no, int weight) {
        this.no = no;
        this.weight = weight;
    }

    public int getNo() {
        return this.no;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.weight - o.weight;
    }
}