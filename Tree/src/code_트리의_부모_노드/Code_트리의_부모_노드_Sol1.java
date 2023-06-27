package code_트리의_부모_노드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Code_트리의_부모_노드_Sol1 {

    private static int n;
    private static Vertex[] unrootedTree;
    private static Node[] rootedTree;

    public static void main(String[] args) throws IOException {
        init();
        convertToRootedTree(1);
        printParents();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(input.readLine());
        unrootedTree = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            unrootedTree[i] = new Vertex(i);
            unrootedTree[i].neighbors = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int vertex1 = Integer.parseInt(tokenizer.nextToken());
            int vertex2 = Integer.parseInt(tokenizer.nextToken());

            unrootedTree[vertex1].neighbors.add(unrootedTree[vertex2]);
            unrootedTree[vertex2].neighbors.add(unrootedTree[vertex1]);
        }

        rootedTree = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            rootedTree[i] = new Node(i);
            rootedTree[i].children = new ArrayList<>();
        }
    }

    private static void convertToRootedTree(int cur) {
        for (Vertex child : unrootedTree[cur].neighbors) {
            if (isParent(cur, child.no)) {
                continue;
            }

            setCompleteEdge(cur, child.no);
            convertToRootedTree(child.no);
        }
    }

    private static void setCompleteEdge(int cur, int child) {
        rootedTree[cur].children.add(rootedTree[child]);
        rootedTree[child].parent = rootedTree[cur];
    }

    private static boolean isParent(int cur, int parent) {
        return rootedTree[cur].parent != null && rootedTree[cur].parent.no == parent;
    }

    private static void printParents() {
        StringBuilder answer = new StringBuilder();

        for (int i = 2; i <= n; i++) {
            answer.append(rootedTree[i].parent.no).append('\n');
        }

        System.out.println(answer);
    }

    private static class Vertex {
        private int no;
        private List<Vertex> neighbors;

        public Vertex(int no) {
            this.no = no;
        }
    }

    private static class Node {
        private int no;
        private Node parent;
        private List<Node> children;

        public Node(int no) {
            this.no = no;
        }
    }


}
