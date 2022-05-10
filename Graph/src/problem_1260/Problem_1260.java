package problem_1260;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_1260 {
    private static class Vertex implements Comparable<Vertex> {
        private int num;
        private TreeSet<Vertex> adjList;

        public Vertex(int num) {
            this.num = num;
            this.adjList = new TreeSet<>();
        }

        public static void addCompleteEdge(Vertex[] graph, int head, int tail) {
            graph[head].adjList.add(graph[tail]);
            graph[tail].adjList.add(graph[head]);
        }

        @Override
        public int compareTo(Vertex o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int V = Integer.parseInt(tokenizer.nextToken());

        Vertex[] graph = new Vertex[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int head = Integer.parseInt(tokenizer.nextToken());
            int tail = Integer.parseInt(tokenizer.nextToken());

            Vertex.addCompleteEdge(graph, head, tail);
        }

        System.out.println(dfs(graph, V));
        System.out.println(bfs(graph, V));
    }

    // 조건을 만족시키려면 후위 순회와 같은 순서로 방문해야 한다.
    private static String dfs(Vertex[] graph, int start) {
        StringBuilder answer = new StringBuilder();
        boolean[] visited = new boolean[graph.length + 1];

        return dfs_recursive(graph, start, visited, answer).toString();
    }

    private static StringBuilder dfs_recursive(Vertex[] graph, int depth, boolean[] visited, StringBuilder answer) {
        visited[depth] = true;
        answer.append(depth).append(' ');

        for (Vertex neighbor : graph[depth].adjList) {
            int next = neighbor.num;

            if (!visited[next]) {
                dfs_recursive(graph, next, visited, answer);
            }
        }

        return answer;
    }

    private static String bfs(Vertex[] graph, int start) {
        StringBuilder answer = new StringBuilder();
        boolean[] visited = new boolean[graph.length + 1];
        Queue<Vertex> vertices = new LinkedList<>();

        visited[graph[start].num] = true;
        vertices.offer(graph[start]);

        while (!vertices.isEmpty()) {
            Vertex cur = vertices.poll();

            for (Vertex neighbor : cur.adjList) {
                int next = neighbor.num;

                if (!visited[next]) {
                    visited[next] = true;
                    vertices.offer(neighbor);
                }
            }

            answer.append(cur.num).append(' ');
        }

        return answer.toString();
    }
}
