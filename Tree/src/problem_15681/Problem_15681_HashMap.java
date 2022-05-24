package problem_15681;

import java.io.*;
import java.util.*;

public class Problem_15681_HashMap {
    private static class Node {
        private int data;
        private Node parent;
        private HashMap<Integer, Node> children;

        public Node(int data) {
            this.data = data;
            children = new HashMap<>();
        }
    }

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        final int N = Integer.parseInt(tokenizer.nextToken());
        final int R = Integer.parseInt(tokenizer.nextToken());
        final int Q = Integer.parseInt(tokenizer.nextToken());

        Node[] tree = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new Node(i);
        }

        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(input.readLine());

            int data1 = Integer.parseInt(tokenizer.nextToken());
            int data2 = Integer.parseInt(tokenizer.nextToken());

            tree[data1].children.put(data2, tree[data2]);
            tree[data2].children.put(data1, tree[data1]);
        }

        tree[R].parent = tree[R];
        makeTree(tree, tree[R]);
        int[] size = cntNodes(tree, R);

        for (int i = 0; i < Q; i++) {
            int rootData = Integer.parseInt(input.readLine());
            answer.append(size[rootData]).append('\n');
        }

        System.out.print(answer);
    }

    private static void makeTree(Node[] tree, Node parent) {
        for (Node child : parent.children.values()) {
            child.parent = parent;
            child.children.remove(parent.data);
            makeTree(tree, child);
        }
    }

    private static int[] cntNodes(Node[] tree, int curData) {
        int[] size = new int[tree.length];
        cntNodes(tree, curData, size);
        return size;
    }

    private static int cntNodes(Node[] tree, int curData, int[] size) {
        size[curData] = 1;

        for (Node child : tree[curData].children.values()) {
            size[curData] += cntNodes(tree, child.data, size);
        }

        return size[curData];
    }
}
