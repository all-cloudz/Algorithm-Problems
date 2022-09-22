package problem_1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_1167 {

    private static class Node {
        private int to;
        private int weight;
        private Node next;

        public Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    private static Node[] tree;
    private static int root;
    private static int maxDist;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        tree = new Node[T + 1];

        for (int i = 1; i <= T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int cur = Integer.parseInt(tokenizer.nextToken());

            int node;
            while ((node = Integer.parseInt(tokenizer.nextToken())) != -1) {
                int weight = Integer.parseInt(tokenizer.nextToken());
                tree[cur] = new Node(node, weight, tree[cur]);
            }
        }

        boolean[] visited = new boolean[T + 1];
        setMaxDist(1, visited, 0);

        visited = new boolean[T + 1];
        setMaxDist(root, visited, 0);

        System.out.println(maxDist);
    }

    private static void setMaxDist(int cur, boolean[] visited, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            root = cur;
        }

        visited[cur] = true;

        for (Node next = tree[cur]; next != null; next = next.next) {
            if (visited[next.to]) {
                continue;
            }

            setMaxDist(next.to, visited, dist + next.weight);
        }
    }

}
