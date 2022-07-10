package problem_d5_1248;

import java.io.*;
import java.util.*;

// 쿼리 1개당 시간복잡도가 O(V)이므로 V가 10만 이상의 큰 값을 갖는 경우에는 활용하기 어렵다.
// 이 문제의 V는 1만 이하의 값을 가지므로 통과!
public class Problem_D5_1248_HashMap {
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

    private static Node findLCA(Node root, Node node1, Node node2) {
        HashMap<Integer, Node> parent = new HashMap<>(); // key : child, value : parent

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node cur = nodes.poll();

            if (cur.left != null) {
                parent.put(cur.left.data, cur);
                nodes.add(cur.left);
            }

            if (cur.right != null) {
                parent.put(cur.right.data, cur);
                nodes.add(cur.right);
            }
        }

        HashSet<Integer> ancestor = new HashSet<>();
        while (node1 != null) {
            ancestor.add(node1.data);
            node1 = parent.get(node1.data);
        }

        while (node2 != null) {
            if (ancestor.contains(node2.data)) {
                return node2;
            }

            node2 = parent.get(node2.data);
        }

        return null;
    }

    private static int cntNodes(Node root) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Node> nodes = new Stack<>();

        visited.add(root.data);
        nodes.push(root);

        int cnt = 1;
        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();

            if (cur.left != null && !visited.contains(cur.left.data)) {
                visited.add(cur.left.data);
                nodes.push(cur.left);
                cnt++;
            }

            if (cur.right != null && !visited.contains(cur.right.data)) {
                visited.add(cur.right.data);
                nodes.push(cur.right);
                cnt++;
            }
        }

        return cnt;
    }
}
