package code_트리의_부모_노드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Code_트리의_부모_노드_Sol2 {

    private static int n;
    private static Node[] tree;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        init();
        convert2RootedTree(1);
        printParents();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        tree = new Node[n + 1];
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new Node(i);
            parents[i] = -1;
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int node1 = Integer.parseInt(tokenizer.nextToken());
            int node2 = Integer.parseInt(tokenizer.nextToken());

            tree[node1].neighbors.add(tree[node2]);
            tree[node2].neighbors.add(tree[node1]);
        }
    }

    private static void convert2RootedTree(int cur) {
        for (Node neighbor : tree[cur].neighbors) {
            if (parents[cur] != -1 && parents[cur] == neighbor.no) {
                continue;
            }

            parents[neighbor.no] = cur;
            convert2RootedTree(neighbor.no);
        }
    }

    private static void printParents() {
        StringBuilder answer = new StringBuilder();

        for (int i = 2; i <= n; i++) {
            answer.append(parents[i]).append('\n');
        }

        System.out.println(answer);
    }

    private static class Node {
        private final int no;
        private final List<Node> neighbors;

        public Node(int no) {
            this.no = no;
            this.neighbors = new ArrayList<>();
        }

    }

}
