package problem_d5_1248;

import java.io.*;
import java.util.*;

// 시간복잡도가 O(VE)이므로 V가 10만 이상의 큰 값을 갖는 경우에는 활용하기 어렵다.
// 이 문제의 V는 1만 이하의 값을 가지므로 통과!
public class Problem_D5_1248_Recursive {
    private static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private static StringBuilder answer = new StringBuilder();
    private static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(input.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int V = Integer.parseInt(tokenizer.nextToken());
            int E = Integer.parseInt(tokenizer.nextToken());
            int node1 = Integer.parseInt(tokenizer.nextToken());
            int node2 = Integer.parseInt(tokenizer.nextToken());

            tree = new Node[V + 1];
            for (int j = 1; j <= V; j++) {
                tree[j] = new Node(j);
            }

            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < E; j++) {
                int parent = Integer.parseInt(tokenizer.nextToken());
                int child = Integer.parseInt(tokenizer.nextToken());
                addEdge(tree[parent], tree[child]);
            }

            Node ancestor = findLCA(tree[1], tree[node1], tree[node2]);
            answer.append(String.format("#%d %d %d%n", i, ancestor.data, cntNodes(ancestor)));
        }

        System.out.print(answer);
    }

    private static void addEdge(Node parent, Node child) {
        if (parent.left == null) {
            parent.left = child;
            return;
        }

        parent.right = child;
    }

    private static Node findLCA(Node cur, Node node1, Node node2) {
        if (cur == null) {
            return null;
        }

        if (cur == node1 || cur == node2) {
            return cur;
        }

        Node left = findLCA(cur.left, node1, node2);
        Node right = findLCA(cur.right, node1, node2);

        if (left != null && right != null) {
            return cur;
        }

        if (right != null) {
            return right;
        }

        if (left != null) {
            return left;
        }

        return null;
    }

    private static int cntNodes(Node cur) {
        int[] subtreeSize = new int[tree.length];
        return cntNodes(cur, subtreeSize);
    }

    private static int cntNodes(Node cur, int[] subtreeSize) {
        subtreeSize[cur.data] = 1;

        if (cur.left != null) {
            subtreeSize[cur.data] += cntNodes(cur.left, subtreeSize);
        }

        if (cur.right != null) {
            subtreeSize[cur.data] += cntNodes(cur.right, subtreeSize);
        }

        return subtreeSize[cur.data];
    }
}
