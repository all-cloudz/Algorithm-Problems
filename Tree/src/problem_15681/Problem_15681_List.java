package problem_15681;

import java.io.*;
import java.util.*;

public class Problem_15681_List {
    private static class Node {
        private int data;
        private Node parent;
        private List<Node> children;

        public Node(int data) {
            this.data = data;
            children = new ArrayList<>();
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

            tree[data1].children.add(tree[data2]);
            tree[data2].children.add(tree[data1]);
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
        for (int i = 0; i < parent.children.size(); i++) {
            Node child = parent.children.get(i);

            if (child == parent.parent) {
                parent.children.remove(i);
                i--;
                continue;
            }

            child.parent = parent;
            makeTree(tree, child);
        }
    }
    
    // DP를 DFS로 수행하기 위해 오버로딩을 수행
    private static int[] cntNodes(Node[] tree, int curData) {
        int[] size = new int[tree.length]; // tabulation of DP
        cntNodes(tree, curData, size);
        return size;
    }
    
    // DFS를 재귀적으로 수행
    private static int cntNodes(Node[] tree, int curData, int[] size) {
        size[curData] = 1;

        for (Node child : tree[curData].children) {
            size[curData] += cntNodes(tree, child.data, size);
        }

        return size[curData];
    }
}
