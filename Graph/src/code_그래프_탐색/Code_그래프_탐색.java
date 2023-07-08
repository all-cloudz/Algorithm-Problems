package code_그래프_탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code_그래프_탐색 {

    private static int N;
    private static Vertex[] vertices;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        vertices = new Vertex[N + 1];
        for (int i = 1; i <= N; i++) {
            vertices[i] = new Vertex(i, null);
        }

        while (M-- > 0) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());

            vertices[from] = new Vertex(to, vertices[from]);
            vertices[to] = new Vertex(from, vertices[to]);
        }

        searchDepthFirst(1, new boolean[N + 1]);
        System.out.println(cnt - 1);
    }

    private static void searchDepthFirst(int label, boolean[] visited) {
        if (visited[label]) {
            return;
        }

        visited[label] = true;
        cnt++;

        for (Vertex cur = vertices[label]; cur != null; cur = cur.nxt) {
            searchDepthFirst(cur.label, visited);
        }
    }

    private static class Vertex {
        private int label;
        private Vertex nxt;

        public Vertex(int label, Vertex nxt) {
            this.label = label;
            this.nxt = nxt;
        }
    }

}
