package problem_1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_1967 {

    private static class Node {
        private int label;
        private int weight;

        public Node(int label, int weight) {
            this.label = label;
            this.weight = weight;
        }
    }

    private static int N;
    private static List<List<Node>> tree;
    private static boolean[] visited;
    private static int lastNode;
    private static int maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int parent = Integer.parseInt(tokenizer.nextToken());
            int child = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());

            tree.get(parent)
                .add(new Node(child, weight));

            tree.get(child)
                .add(new Node(parent, weight));
        }

        visited = new boolean[N + 1];
        maxLength = Integer.MIN_VALUE;
        dfs(1, 0);

        visited = new boolean[N + 1];
        maxLength = Integer.MIN_VALUE;
        dfs(lastNode, 0);

        System.out.println(maxLength);
    }

    private static void dfs(int cur, int length) {
        if (visited[cur]) {
            return;
        }

        visited[cur] = true;

        for (Node child : tree.get(cur)) {
            dfs(child.label, length + child.weight);
        }

        if (maxLength < length) {
            lastNode = cur;
            maxLength = length;
        }
    }

}
