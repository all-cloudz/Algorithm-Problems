package problem_d6_1855;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_D6_1855 {

    private static class Node {
        private int data;
        private Node parent;
        private List<Node> children;

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    private static int N;
    private static Node[] tree;
    private static int[] depths;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        final int T = Integer.parseInt(input.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(input.readLine());
            tree = new Node[N + 1];

            for (int i = 1; i <= N; i++) {
                tree[i] = new Node(i);
            }

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int i = 2; i <= N; i++) {
                int parentIdx = Integer.parseInt(tokenizer.nextToken());
                tree[i].parent = tree[parentIdx];
                tree[parentIdx].children.add(tree[i]);
            }

            depths = new int[N + 1];
            cache = new int[N + 1][21];
            makeTable();

            answer.append(String.format("#%d %d%n", tc, traverseTree()));
        }

        System.out.println(answer);
    }

    private static long traverseTree() {
        Queue<Node> nodes = new ArrayDeque<>();
        boolean[] discovered = new boolean[N + 1];

        nodes.add(tree[1]);
        discovered[1] = true;

        long dist = 0L;
        Node prev = null;
        Node cur = null;

        while (!nodes.isEmpty()) {
            prev = cur;
            cur = nodes.poll();

            if (prev != null) {
                dist += getDist(cur.data, prev.data);
            }

            for (Node next : cur.children) {
                if (!discovered[next.data]) {
                    nodes.add(next);
                    discovered[next.data] = true;
                }
            }
        }

        return dist;
    }

    private static long getDist(int from, int to) {
        int lca = findLCA(from, to);
        return depths[from] + depths[to] - (depths[lca] << 1);
    }

    private static int findLCA(int node1, int node2) {
        if (depths[node1] > depths[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        for (int i = 20; i >= 0; i--) {
            int jump = 1 << i;

            if (depths[node2] - depths[node1] >= jump) {
                node2 = cache[node2][i];
            }
        }

        if (node1 == node2) {
            return node1;
        }

        for (int i = 20; i >= 0; i--) {
            if (cache[node1][i] == cache[node2][i]) {
                continue;
            }

            node1 = cache[node1][i];
            node2 = cache[node2][i];
        }

        return cache[node1][0];
    }

    private static void makeTable() {
        Stack<Node> nodes = new Stack<>();
        nodes.push(tree[1]);
        depths[tree[1].data] = 0;

        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();

            for (Node next : cur.children) {
                cache[next.data][0] = cur.data;
                depths[next.data] = depths[cur.data] + 1;
                nodes.push(next);
            }
        }

        for (int i = 1; i < 21; i++) {
            for (int j = 1; j <= N; j++) {
                int parentIdx = cache[j][i - 1];
                cache[j][i] = cache[parentIdx][i - 1];
            }
        }
    }

//    private static long getDist(Node from, Node to) {
//        if (from.parent == null) {
//            return 0;
//        }
//
//        Queue<Node> nodes = new ArrayDeque<>();
//        boolean[] discovered = new boolean[N + 1];
//
//        nodes.add(from);
//        discovered[from.data] = true;
//        long dist = 0;
//
//        dfs :
//        while (!nodes.isEmpty()) {
//            for (int size = nodes.size(), i = 0; i < size; i++) {
//                Node cur = nodes.poll();
//
//                if (cur == to) {
//                    break dfs;
//                }
//
//                for (Node next : cur.children) {
//                    if (!discovered[next.data]) {
//                        nodes.add(next);
//                        discovered[next.data] = true;
//                    }
//                }
//
//                if (cur.parent != null && !discovered[cur.parent.data]) {
//                    nodes.add(cur.parent);
//                    discovered[cur.parent.data] = true;
//                }
//            }
//
//            dist++;
//        }
//
//        return dist;
//    }

}
