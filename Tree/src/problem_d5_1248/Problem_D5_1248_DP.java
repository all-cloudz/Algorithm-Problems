package problem_d5_1248;

import java.io.*;
import java.util.*;

// 쿼리 1개당 시간복잡도가 O(logV)이므로 10만 이상의 V가 들어와도 통과 가능하다.
public class Problem_D5_1248_DP {
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

            Node ancestor = findLCA(node1, node2);
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

    private static Node findLCA(int node1, int node2) {
        int[] depth = new int[tree.length];
        int[][] parent = makeTable(tree, depth);

        // node2의 깊이가 node1의 깊이보다 더 깊도록 설정
        if (depth[node1] > depth[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        // node2의 깊이가 node1의 깊이와 같도록 조정
        for (int i = 20; i >= 0; i--) {
            int jump = 1 << i;
            if (depth[node2] - depth[node1] >= jump) {
                node2 = parent[node2][i];
            }
        }

        if (node1 == node2) {
            return tree[node1];
        }


        // node1과 node2의 깊이에서 2^i만큼 위에 있는 노드가 다르면 node1과 node2를 그 깊이로 이동시키는 것을 반복하면 최종적으로 node1과 node2의 부모가 같게 된다.
        for (int i = 20; i >= 0; i--) {
            if (parent[node1][i] == parent[node2][i]) {
                continue;
            }

            node1 = parent[node1][i];
            node2 = parent[node2][i];
        }

        return tree[parent[node1][0]];
    }

    private static int[][] makeTable(Node[] tree, int[] depth) {
        /* 아래의 parent에서 21의 의미는 다음과 같다.
         * 1. 노드의 수가 100만이면 리프 노드부터 루트 노드까지 최대 약 100만개의 노드가 있을 수 있다.
         * 2. 리프 노드에서 루트 노드까지 한 번에 거슬러 올라가려면 2^i = 100만이 되도록 하는 i의 값을 구해야 한다. 이때 i의 값은 약 20이다.
         * 3. 따라서 최대 노드의 수에 따라 문제에 다르게 접근하게 될텐데 노드의 수가 100만 이하인 경우 21을 사용하면 된다. */
        int[][] parent = new int[tree.length][21];

        Stack<Node> nodes = new Stack<>();
        nodes.push(tree[1]); // 루트 노드가 1이므로 트리 순회를 위해 처음에 push
        depth[1] = 0; // 루트 노드의 깊이는 0

        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();

            if (cur.left != null) {
                parent[cur.left.data][0] = cur.data;
                nodes.push(cur.left);
                depth[cur.left.data] = depth[cur.data] + 1;
            }

            if (cur.right != null) {
                parent[cur.right.data][0] = cur.data;
                nodes.push(cur.right);
                depth[cur.right.data] = depth[cur.data] + 1;
            }
        }

        return fillParents(parent);
    }

    private static int[][] fillParents(int[][] parent) {
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j < parent.length; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }

        return parent;
    }

    private static int cntNodes(Node cur) {
        int cnt = 1;

        if (cur.left != null) {
            cnt += cntNodes(cur.left);
        }

        if (cur.right != null) {
            cnt += cntNodes(cur.right);
        }

        return cnt;
    }
}
