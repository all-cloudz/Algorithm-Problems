package problem_d5_1248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_D5_1248_DP_Review {

    private static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int V = Integer.parseInt(tokenizer.nextToken());
            int E = Integer.parseInt(tokenizer.nextToken());
            int node1 = Integer.parseInt(tokenizer.nextToken());
            int node2 = Integer.parseInt(tokenizer.nextToken());

            Node[] tree = new Node[V + 1];
            for (int i = 1; i <= V; i++) {
                tree[i] = new Node(i);
            }

            tokenizer = new StringTokenizer(input.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(tokenizer.nextToken());
                int child = Integer.parseInt(tokenizer.nextToken());
                addEdge(tree[parent], tree[child]);
            }

            Node commonAncestor = findLCA(tree, node1, node2);
            answer.append(String.format("#%d %d %d%n", tc, commonAncestor.data, countSubTreeNodes(commonAncestor)));
        }

        System.out.println(answer);
    }

    private static void addEdge(Node parent, Node child) {
        if (parent.left == null) {
            parent.left = child;
            return;
        }

        parent.right = child;
    }

    private static Node findLCA(Node[] tree, int node1, int node2) {
        int[] depth = new int[tree.length];
        int[][] cache = makeTable(tree, depth);

        if (depth[node1] > depth[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        for (int i = 20; i >= 0; i--) {
            int jump = 1 << i;

            if (depth[node2] - depth[node1] >= jump) {
                node2 = cache[node2][i];
            }
        }

        if (node1 == node2) {
            return tree[node1];
        }

        for (int i = 20; i >= 0; i--) {
            if (cache[node1][i] == cache[node2][i]) {
                continue;
            }

            node1 = cache[node1][i];
            node2 = cache[node2][i];
        }

        return tree[cache[node1][0]];
    }

    private static int[][] makeTable(Node[] tree, int[] depth) {
        int[][] cache = new int[tree.length][21]; // 21단계 상위의 부모까지 저장해둘 배열

        Stack<Node> nodes = new Stack<>();
        nodes.push(tree[1]); // 루트 노드부터 탐색 시작
        depth[1] = 0; // 루트의 깊이는 0

        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();

            if (cur.left != null) {
                cache[cur.left.data][0] = cur.data;
                nodes.push(cur.left);
                depth[cur.left.data] = depth[cur.data] + 1;
            }

            if (cur.right != null) {
                cache[cur.right.data][0] = cur.data;
                nodes.push(cur.right);
                depth[cur.right.data] = depth[cur.data] + 1;
            }
        }

        for (int i = 1; i < 21; i++) {
            for (int j = 1; j < cache.length; j++) {
                int prevParent = cache[j][i - 1];
                cache[j][i] = cache[prevParent][i - 1];
            }
        }

        return cache;
    }

    private static int countSubTreeNodes(Node commonAncestor) {
        int count = 1;

        if (commonAncestor.left != null) {
            count += countSubTreeNodes(commonAncestor.left);
        }

        if (commonAncestor.right != null) {
            count += countSubTreeNodes(commonAncestor.right);
        }

        return count;
    }

}
