package problem_1068;

import java.io.*;
import java.util.*;

public class Problem_1068 {
    private static class Node {
        private int data;
        private List<Node> children;
        private Node parent;

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
            this.parent = this;
        }
    }

    private static Node[] tree;
    private static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int V = Integer.parseInt(input.readLine());
        tree = new Node[V];
        for (int i = 0; i < V; i++) {
            tree[i] = new Node(i);
        }

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < V; i++) {
            int parent = Integer.parseInt(tokenizer.nextToken());

            if (parent == -1) {
                root = tree[i];
                continue;
            }

            tree[parent].children.add(tree[i]);
            tree[i].parent = tree[parent];
        }

        deleteNode(tree[Integer.parseInt(input.readLine())]);
        System.out.print(countLeafs());
    }

    private static void deleteNode(Node target) {
        if (target == root) {
            root = null;
            return;
        }

        target.parent.children.remove(target);
    }

    private static int countLeafs() {
        if (root == null) {
            return 0;
        }

        int cnt = 0;
        boolean[] visited = new boolean[tree.length];
        Stack<Node> nodes = new Stack<>();

        nodes.push(root);
        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();
            visited[cur.data] = true;

            if (cur.children.isEmpty()) {
                cnt++;
                continue;
            }

            for (Node next : cur.children) {
                if (!visited[next.data]) {
                    nodes.add(next);
                }
            }
        }

        return cnt;
    }
}
